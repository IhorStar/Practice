package ua.nure.starodubets.SummaryTask4.controller.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.starodubets.SummaryTask4.dao.CourseDAO;
import ua.nure.starodubets.SummaryTask4.dao.DAOException;
import ua.nure.starodubets.SummaryTask4.dao.DAOFactory;
import ua.nure.starodubets.SummaryTask4.model.Course;

/**
 * Servlet implementation class SortCourseServlet
 */
public class SortCourseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(SortCourseServlet.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sortBy = request.getParameter("sortBy");
		CourseDAO courseDAO = DAOFactory.getInstance().getCourseDAO();

		try {
			if(sortBy.equals("asc")) {
				List<Course> coursesList = courseDAO.getAllCourseOrderByNameAsc();
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/catalogue.jsp");
				request.setAttribute("allCourses", coursesList);
				dispatcher.forward(request, response);
			}
			if(sortBy.equals("desc")) {
				List<Course> coursesList = courseDAO.getAllCourseOrderByNameDesc();
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/catalogue.jsp");
				request.setAttribute("allCourses", coursesList);
				dispatcher.forward(request, response);
			}
			if(sortBy.equals("duration")) {
				List<Course> coursesList = courseDAO.getAllCourseOrderByDuration();
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/catalogue.jsp");
				request.setAttribute("allCourses", coursesList);
				dispatcher.forward(request, response);
			}
			if(sortBy.equals("numberStudents")) {
				List<Course> coursesList = courseDAO.getAllCourseOrderByNumberStudents();
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/catalogue.jsp");
				request.setAttribute("allCourses", coursesList);
				dispatcher.forward(request, response);
			}
		} catch (DAOException e) {
			LOGGER.error("Failed to exctract all courses from the database", e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
