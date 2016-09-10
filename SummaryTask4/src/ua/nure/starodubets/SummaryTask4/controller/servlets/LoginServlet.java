package ua.nure.starodubets.SummaryTask4.controller.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
import ua.nure.starodubets.SummaryTask4.model.Journal;
import ua.nure.starodubets.SummaryTask4.model.Subject;
import ua.nure.starodubets.SummaryTask4.model.User;
import ua.nure.starodubets.SummaryTask4.service.AuthorizationService;
import ua.nure.starodubets.SummaryTask4.service.impl.AuthorizationServiceImpl;

/**
 * Servlet implementation class LoginServlet
 *
 * @author Ihor Starodubets
 */
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

	private static final String ADMIN_HOME = "admin";

	private static final String TEACHER_HOME = "teacher";

	private static final String STUDENT_HOME = "student";

	private static final String LOGIN_PAGE = "/login";

	private static final int TEACHER_ROLE_ID = 2;

	private static final int STUDENT_ROLE_ID = 3;

	private static final int ACTIVE_USER = 1;

	private static final int BLOCKED_USER = 2;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		AuthorizationService authorization = new AuthorizationServiceImpl();
		DAOFactory factory = DAOFactory.getInstance();
		UserDAO userDAO = factory.getUserDAO();
		SubjectDAO subjectDAO = factory.getSubjectDAO();
		CourseDAO courseDAO = factory.getCourseDAO();
		JournalDAO journalDAO = factory.getJournalDAO();

		try {
			User user = userDAO.getUserByEmailAndPassword(email, password);
			if(user.getEmail() != null && user.getPassword() != null) {
				LOGGER.info("User with email " + email + " successfully logged in");
				if(authorization.isAdmin(user)) {
					List<User> teachersList = userDAO.getAllUsersByRoleId(TEACHER_ROLE_ID);
					List<User> activeStudentsList = userDAO.getAllUsersByStatusAndRoleId(ACTIVE_USER, STUDENT_ROLE_ID);
					List<User> blockedStudentsList = userDAO.getAllUsersByStatusAndRoleId(BLOCKED_USER, STUDENT_ROLE_ID);
					List<Subject> subjectsList = subjectDAO.getAllSubjects();
					List<Course> coursesList = courseDAO.getAllCourse();
					HttpSession session = request.getSession();
					session.setAttribute("user", user);
					session.setAttribute("allTeachers", teachersList);
					session.setAttribute("allActiveStudents", activeStudentsList);
					session.setAttribute("allBlockedStudents", blockedStudentsList);
					session.setAttribute("allSubjects", subjectsList);
					session.setAttribute("allCourses", coursesList);
					response.sendRedirect(ADMIN_HOME);
				}
				if(authorization.isTeacher(user)) {
					List<Journal> journalsList = journalDAO.getAllJournalsByTeacherId(user.getId());
					Set<Integer> allTeacherCourses = getAllTeacherCourses(journalsList);
					HttpSession session = request.getSession();
					session.setAttribute("user", user);
					session.setAttribute("allJournals", journalsList);
					session.setAttribute("courseDAO", courseDAO);
					session.setAttribute("userDAO", userDAO);
					session.setAttribute("journalDAO", journalDAO);
					session.setAttribute("allTeacherCourses", allTeacherCourses);
					response.sendRedirect(TEACHER_HOME);
				}
				if(authorization.isStudent(user)) {
					if(user.getStatus() == ACTIVE_USER) {
						List<Journal> journalsList = journalDAO.getAllJournalsByStudentId(user.getId());
						HttpSession session = request.getSession();
						session.setAttribute("user", user);
						session.setAttribute("allJournals", journalsList);
						session.setAttribute("courseDAO", courseDAO);
						session.setAttribute("userDAO", userDAO);
						session.setAttribute("currentDate", getCurrentDate());
						response.sendRedirect(STUDENT_HOME);
					} else {
						LOGGER.info("User is blocked");
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(LOGIN_PAGE);
						request.setAttribute("error", "Your.account.is.blocked");
						dispatcher.include(request, response);
					}
				}
			} else {
				LOGGER.info("User with email " + email + " could not log in");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(LOGIN_PAGE);
				request.setAttribute("error", "Incorrect.email.or.password");
				dispatcher.include(request, response);
			}
		} catch (DAOException e) {
			LOGGER.error("Failed to exctract user with email " + email + " and password " + password + " from the database");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(LOGIN_PAGE);
			request.setAttribute("error", "Incorrect.email.or.password");
			dispatcher.include(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public java.sql.Date getCurrentDate() {
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		return sqlDate;

	}

	public Set<Integer> getAllTeacherCourses(List<Journal> journalsList) {
		Set<Integer> allTeacherCourses = new TreeSet<Integer>();
		for(Journal journal : journalsList) {
			allTeacherCourses.add(journal.getCourseId());
		}
		return allTeacherCourses;
	}
}
