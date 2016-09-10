package ua.nure.starodubets.SummaryTask4.controller.servlets;

import java.io.IOException;
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
 * Servlet implementation class DeleteCourseServlet
 */
public class DeleteCourseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(DeleteCourseServlet.class);

	private static final String ADMIN_COURSE_PAGE = "admin/course";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int courseId = Integer.parseInt(request.getParameter("courseName"));
		CourseDAO courseDAO = DAOFactory.getInstance().getCourseDAO();
		try {
			courseDAO.deleteCourseById(courseId);
			LOGGER.info("Course successfully deleted from the database");
			List<Course> coursesList = courseDAO.getAllCourse();
			HttpSession session = request.getSession();
			session.setAttribute("allCourses", coursesList);
			session.setAttribute("successDeleteCourse", "Course.successfully.deleted");
			response.sendRedirect(ADMIN_COURSE_PAGE);
		} catch (DAOException e) {
			LOGGER.error("Failed to delete course", e);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + ADMIN_COURSE_PAGE);
			request.setAttribute("errorDeleteCourse", "Failed.to.delete.course");
			dispatcher.include(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
