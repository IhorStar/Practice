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
 * Servlet implementation class SaveTeacherServlet
 *
 * @author Ihor Starodubets
 */
public class AddTeacherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(AddTeacherServlet.class);

	private static final int TEACHER_ROLE_ID = 2;

	private static final String ADD_TEACHER_PAGE = "admin/addTeacher";

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
		user.setRoleId(TEACHER_ROLE_ID);

		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getUserDAO();
		try {
			userDAO.saveTeacher(user);
			LOGGER.info("User registered with email: " + user.getEmail());
			List<User> teachersList = userDAO.getAllUsersByRoleId(TEACHER_ROLE_ID);
			HttpSession session = request.getSession();
			session.setAttribute("allTeachers", teachersList);
			session.setAttribute("success", "New.teacher.successfully.registered");
			response.sendRedirect(ADD_TEACHER_PAGE);
		} catch (DAOException e) {
			LOGGER.error("Failed to save user with email: " + user.getEmail());
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + ADD_TEACHER_PAGE);
			request.setAttribute("error", "Failed.to.save.new.teacher");
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
