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
 * Servlet implementation class RegistrationServlet
 *
 * @author Ihor Starodubets
 */
public class RegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(RegistrationServlet.class);

	private static final String REGISTRATION_PAGE = "registration";


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");

		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);

		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getUserDAO();
		try {
			List<String> emailList = userDAO.getAllEmail();
			if(!emailList.contains(email)) {
				userDAO.saveUser(user);
				LOGGER.info("User registered with email: " + user.getEmail());
				HttpSession session = request.getSession();
				session.setAttribute("success", "You.are.successfully.registered");
				response.sendRedirect(REGISTRATION_PAGE);
			} else {
				LOGGER.error("Failed to save user with email: " + user.getEmail());
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + REGISTRATION_PAGE);
				request.setAttribute("error", "User.with.this.email.already.exists");
				dispatcher.include(request, response);
			}

		} catch (DAOException e) {
			LOGGER.error("Failed to save user with email: " + user.getEmail());
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(REGISTRATION_PAGE);
			request.setAttribute("error", "Failed.to.save.user");
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
