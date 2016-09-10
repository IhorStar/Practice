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
 * Servlet implementation class SelectCourseBySubjectServlet
 */
public class SelectCourseBySubjectServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(SortCourseServlet.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int subjectId = Integer.parseInt(request.getParameter("selectBySubject"));
		CourseDAO courseDAO = DAOFactory.getInstance().getCourseDAO();
		try {
			List<Course> coursesList = courseDAO.getAllCourseBySubjectId(subjectId);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/catalogue.jsp");
			request.setAttribute("allCourses", coursesList);
			dispatcher.forward(request, response);
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
