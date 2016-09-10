package ua.nure.starodubets.SummaryTask4.service;

import ua.nure.starodubets.SummaryTask4.model.User;

/**
 *
 * @author Ihor Starodubets
 *
 */
public interface AuthorizationService {

	/**
	 * Method checks user role
	 *
	 * @param user
	 * @return true if user is admin
	 */
	boolean isAdmin(User user);

	/**
	 * Method checks user role
	 *
	 * @param user
	 * @return true if user is teacher
	 */
	boolean isTeacher(User user);

	/**
	 * Method checks user role
	 *
	 * @param user
	 * @return true if user is student
	 */
    boolean isStudent(User user);

}
