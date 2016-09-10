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
import ua.nure.starodubets.SummaryTask4.dao.JournalDAO;
import ua.nure.starodubets.SummaryTask4.dao.SubjectDAO;
import ua.nure.starodubets.SummaryTask4.dao.UserDAO;
import ua.nure.starodubets.SummaryTask4.model.Course;
import ua.nure.starodubets.SummaryTask4.model.Subject;
import ua.nure.starodubets.SummaryTask4.model.User;

/**
 * Servlet implementation class DisplayServlet
 */
public class DisplayServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(DisplayServlet.class);

	private static final int TEACHER_ROLE_ID = 2;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getUserDAO();
		CourseDAO courseDAO = factory.getCourseDAO();
		SubjectDAO subjectDAO = factory.getSubjectDAO();
		JournalDAO journalDAO = factory.getJournalDAO();
		try {
			List<Course> coursesList = courseDAO.getAllCourse();
			List<Subject> subjectsList = subjectDAO.getAllSubjects();
			List<User> teachersList = userDAO.getAllUsersByRoleId(TEACHER_ROLE_ID);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/catalogue.jsp");
			HttpSession session = request.getSession();
			session.setAttribute("allSubjects", subjectsList);
			session.setAttribute("allCourses", coursesList);
			session.setAttribute("allTeachers", teachersList);
			session.setAttribute("userDAO", userDAO);
			session.setAttribute("journalDAO", journalDAO);
			dispatcher.include(request, response);
		} catch (DAOException e) {
			LOGGER.error("Failed to extract data from the database", e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
