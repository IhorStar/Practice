package ua.nure.starodubets.SummaryTask4.controller.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
 * Servlet implementation class UpdateCourseServlet
 */
public class UpdateCourseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(UpdateCourseServlet.class);

	private static final String ADMIN_COURSE_PAGE = "admin/course";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Course course = new Course();
		course.setId(Integer.parseInt(request.getParameter("courseName")));
		course.setCourseStartDate(parseDate(request.getParameter("courseStartDate")));
		course.setCourseEndDate(parseDate(request.getParameter("courseEndDate")));
		course.setDuration(Integer.parseInt(request.getParameter("courseDuration")));
		course.setTeacherId(Integer.parseInt(request.getParameter("courseTeacher")));

		CourseDAO courseDAO = DAOFactory.getInstance().getCourseDAO();
		try {
			courseDAO.updateCourseInfo(course);
			LOGGER.info("Course info successfully updated in the database");
			HttpSession session = request.getSession();
			session.setAttribute("success", "Course.info.successfully.updated");
			response.sendRedirect(ADMIN_COURSE_PAGE);
		} catch (DAOException e) {
			LOGGER.error("Failed to update course info", e);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + ADMIN_COURSE_PAGE);
			request.setAttribute("error", "Failed.to.update.course.info");
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
