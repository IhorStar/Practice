package ua.nure.starodubets.SummaryTask4.controller.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.starodubets.SummaryTask4.dao.CourseDAO;
import ua.nure.starodubets.SummaryTask4.dao.DAOException;
import ua.nure.starodubets.SummaryTask4.dao.DAOFactory;
import ua.nure.starodubets.SummaryTask4.model.Course;

/**
 * Servlet implementation class AddCourseServlet
 */
public class AddCourseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(AddCourseServlet.class);

	private static final String ADMIN_COURSE_PAGE = "admin/course";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String subjectId = request.getParameter("subjectName");
		String courseStartDate = request.getParameter("courseStartDate");
		String courseEndDate = request.getParameter("courseEndDate");
		String courseDuration = request.getParameter("courseDuration");
		String courseTeacher = request.getParameter("courseTeacher");

		Course course = new Course();
		course.setName(name);
		course.setSubjectId(Integer.parseInt(subjectId));
		course.setCourseStartDate(parseDate(courseStartDate));
		course.setCourseEndDate(parseDate(courseEndDate));
		course.setDuration(Integer.parseInt(courseDuration));
		course.setTeacherId(Integer.parseInt(courseTeacher));

		CourseDAO courseDAO = DAOFactory.getInstance().getCourseDAO();
		try {
			courseDAO.saveCourse(course);
			LOGGER.info("New course successfully saved to the database");
			List<Course> coursesList = courseDAO.getAllCourse();
			HttpSession session = request.getSession();
			session.setAttribute("allCourses", coursesList);
			session.setAttribute("success", "New.course.successfully.added");
			response.sendRedirect(ADMIN_COURSE_PAGE);
		} catch (DAOException e) {
			LOGGER.error("Failed to save new course to the database", e);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + ADMIN_COURSE_PAGE);
			request.setAttribute("error", "Failed.to.add.new.course");
			dispatcher.include(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public java.sql.Date parseDate(String date) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Date parsedDate = null;
		try {
			parsedDate = format.parse(date);
		} catch (ParseException e) {
			LOGGER.error("Failed to parse date", e);
		}
		return new java.sql.Date(parsedDate.getTime());

	}

}
