package ua.nure.starodubets.SummaryTask4.dao;

import java.util.List;

import ua.nure.starodubets.SummaryTask4.model.User;

/**
 *
 * @author Ihor Starodubets
 *
 */
public interface UserDAO {

	/**
	 * Method saves new user to database
	 *
	 * @param user user which need save to database
	 * @throws DAOException
	 */
	void saveUser(User user) throws DAOException;

	/**
	 * Method returns user from database by email and password
	 *
	 * @param email
	 * @param password
	 * @return
	 * @throws DAOException
	 */
	User getUserByEmailAndPassword(String email, String password) throws DAOException;

	/**
	 * Method saves teacher to database
	 *
	 * @param user
	 * @throws DAOException
	 */
	void saveTeacher(User user) throws DAOException;

	/**
	 * Method updates user info
	 *
	 * @param user
	 * @throws DAOException
	 */
	void updateUserInfo(User user) throws DAOException;

	/**
	 * Method returns all user by roleId
	 *
	 * @param roleId
	 * @return List of users
	 * @throws DAOException
	 */
	List<User> getAllUsersByRoleId(int roleId) throws DAOException;

	/**
	 * Method returns all user by statusId and roleId
	 *
	 * @param isActive
	 * @param roleId
	 * @return list of users
	 * @throws DAOException
	 */
	List<User> getAllUsersByStatusAndRoleId(int isActive, int roleId) throws DAOException;

	/**
	 * Method updates user status
	 *
	 * @param user
	 * @throws DAOException
	 */
	void updateUserStatus(User user) throws DAOException;

	/**
	 * Method returns user by id
	 *
	 * @param userId id
	 * @return user
	 * @throws DAOException
	 */
	User getUserById(int userId) throws DAOException;

	/**
	 * Method returns all email from table users
	 *
	 * @return list of email
	 * @throws DAOException
	 */
	List<String> getAllEmail() throws DAOException;
}
