package ua.nure.starodubets.SummaryTask4.controller.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.starodubets.SummaryTask4.dao.DAOException;
import ua.nure.starodubets.SummaryTask4.dao.DAOFactory;
import ua.nure.starodubets.SummaryTask4.dao.JournalDAO;
import ua.nure.starodubets.SummaryTask4.model.Journal;

/**
 * Servlet implementation class UpdateRatingServlet
 */
public class UpdateRatingServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(UpdateRatingServlet.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rating = Integer.parseInt(request.getParameter("rating"));
		int studentId = Integer.parseInt(request.getParameter("studentId"));
		int courseId = Integer.parseInt(request.getParameter("courseId"));

		Journal journal = new Journal();
		journal.setCourseId(courseId);
		journal.setStudentId(studentId);
		journal.setRating(rating);

		JournalDAO journalDAO = DAOFactory.getInstance().getJournalDAO();
		try {
			journalDAO.updateRating(journal);
			LOGGER.info("Rating successfully updated in the database");
			response.sendRedirect("teacher/journals");
		} catch (DAOException e) {
			LOGGER.error("Failed to update rating", e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
