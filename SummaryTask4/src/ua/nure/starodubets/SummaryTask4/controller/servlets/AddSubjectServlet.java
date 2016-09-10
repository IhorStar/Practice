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

import ua.nure.starodubets.SummaryTask4.dao.DAOException;
import ua.nure.starodubets.SummaryTask4.dao.DAOFactory;
import ua.nure.starodubets.SummaryTask4.dao.SubjectDAO;
import ua.nure.starodubets.SummaryTask4.model.Subject;

/**
 * Servlet implementation class AddSubjectServlet
 */
public class AddSubjectServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(AddSubjectServlet.class);

	private static final String ADMIN_COURSE_PAGE = "admin/course";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String subjectName = request.getParameter("subject");

		Subject subject = new Subject();
		subject.setName(subjectName);

		SubjectDAO subjectDAO = DAOFactory.getInstance().getSubjectDAO();

		try {
			subjectDAO.saveSubject(subject);
			LOGGER.info("New subject successfully saved in the database");
			List<Subject> subjectsList = subjectDAO.getAllSubjects();
			HttpSession session = request.getSession();
			session.setAttribute("allSubjects", subjectsList);
			session.setAttribute("success", "New.subject.successfully.added");
			response.sendRedirect(ADMIN_COURSE_PAGE);
		} catch (DAOException e) {
			LOGGER.error("Failed to save new subject to the database", e);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + ADMIN_COURSE_PAGE);
			request.setAttribute("error", "Failed.to.add.new.subject");
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
