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
import ua.nure.starodubets.SummaryTask4.model.Journal;
import ua.nure.starodubets.SummaryTask4.model.User;

/**
 * Servlet implementation class RegisterForCourseServlet
 */
public class RegisterForCourseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(RegisterForCourseServlet.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int courseId = Integer.parseInt(request.getParameter("courseId"));
		int teacherId = Integer.parseInt(request.getParameter("teacherId"));
		int studentId = Integer.parseInt(request.getParameter("studentId"));

		Journal journal = new Journal();
		journal.setCourseId(courseId);
		journal.setTeacherId(teacherId);
		journal.setStudentId(studentId);

		DAOFactory factory = DAOFactory.getInstance();
		JournalDAO journalDAO = factory.getJournalDAO();
		CourseDAO courseDAO = factory.getCourseDAO();
		try {
			journalDAO.saveJournal(journal);
			LOGGER.info("Student successfully registered for the course");
			courseDAO.updateNumberStudentsByCourseId(courseId);
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			List<Journal> journalsList = journalDAO.getAllJournalsByStudentId(user.getId());
			session.setAttribute("allJournals", journalsList);
			response.sendRedirect("catalogue");
		} catch (DAOException e) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("catalogue");
			request.setAttribute("error", "Failed to register for the course");
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
