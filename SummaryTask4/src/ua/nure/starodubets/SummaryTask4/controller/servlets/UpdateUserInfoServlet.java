package ua.nure.starodubets.SummaryTask4.controller.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.starodubets.SummaryTask4.dao.DAOException;
import ua.nure.starodubets.SummaryTask4.dao.DAOFactory;
import ua.nure.starodubets.SummaryTask4.dao.UserDAO;
import ua.nure.starodubets.SummaryTask4.model.User;

/**
 * Servlet implementation class UpdateUserInfoServlet
 */
public class UpdateUserInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(UpdateUserInfoServlet.class);

	private static final String ADMIN_HOME = "admin";

	private static final String TEACHER_HOME = "teacher";

	private static final String STUDENT_HOME = "student";

	private static final String UPDATE_PROFILE_PAGE = "/update";

	private static final int ADMIN_ROLE_ID = 1;

	private static final int TEACHER_ROLE_ID = 2;

	private static final int STUDENT_ROLE_ID = 3;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		user.setEmail(request.getParameter("email"));
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		user.setPassword(request.getParameter("password"));
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getUserDAO();
		try {
			userDAO.updateUserInfo(user);
			LOGGER.info("User info successfully updated in the database");
			session.setAttribute("success", "User.info.successfully.updated");
			if(user.getRoleId() == ADMIN_ROLE_ID) {
				response.sendRedirect(ADMIN_HOME);
			}
			if(user.getRoleId() == TEACHER_ROLE_ID) {
				response.sendRedirect(TEACHER_HOME);
			}
			if(user.getRoleId() == STUDENT_ROLE_ID) {
				response.sendRedirect(STUDENT_HOME);
			}
		} catch (DAOException e) {
			LOGGER.error("Failed to update user info");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(UPDATE_PROFILE_PAGE);
			request.setAttribute("error", "Failed.to.update.user.info");
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
