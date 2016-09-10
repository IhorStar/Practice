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
import ua.nure.starodubets.SummaryTask4.dao.UserDAO;
import ua.nure.starodubets.SummaryTask4.model.User;

/**
 * Servlet implementation class BlockUserServlet
 */
public class BlockUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(BlockUserServlet.class);

	private static final String BLOCK_USER_PAGE = "admin/student";

	private static final int STUDENT_ROLE_ID = 3;

	private static final int ACTIVE_USER = 1;

	private static final int BLOCKED_USER = 2;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int userId = Integer.parseInt(request.getParameter("activeUser"));

		UserDAO userDAO = DAOFactory.getInstance().getUserDAO();
		try {
			User user = userDAO.getUserById(userId);
			user.setStatus(BLOCKED_USER);
			userDAO.updateUserStatus(user);
			LOGGER.info("User status successfully updated");
			List<User> activeStudentsList = userDAO.getAllUsersByStatusAndRoleId(ACTIVE_USER, STUDENT_ROLE_ID);
			List<User> blockedStudentsList = userDAO.getAllUsersByStatusAndRoleId(BLOCKED_USER, STUDENT_ROLE_ID);
			HttpSession session = request.getSession();
			session.setAttribute("allActiveStudents", activeStudentsList);
			session.setAttribute("allBlockedStudents", blockedStudentsList);
			session.setAttribute("success", "User.successfully.blocked");
			response.sendRedirect(BLOCK_USER_PAGE);
		} catch (DAOException e) {
			LOGGER.error("Failed to update user status", e);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + BLOCK_USER_PAGE);
			request.setAttribute("error", "Failed.to.block.user");
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
