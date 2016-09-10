package ua.nure.starodubets.SummaryTask4.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.starodubets.SummaryTask4.dao.DAOException;
import ua.nure.starodubets.SummaryTask4.dao.DAOFactory;
import ua.nure.starodubets.SummaryTask4.dao.UserDAO;
import ua.nure.starodubets.SummaryTask4.model.User;

/**
 * Class manipulates table users
 *
 * @author Ihor Starodubets
 *
 */
public class UserDAOImpl implements UserDAO {

	private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class);

	private static final String SAVE_USER = "INSERT INTO users (email, password, first_name, last_name) VALUES (?, ?, ?, ?)";

	private static final String GET_USER_BY_EMAIL_AND_PASSWORD = "SELECT * from users WHERE email = ? AND password = ?";

	private static final String SAVE_TEACHER = "INSERT INTO users (email, password, first_name, last_name, role_id) VALUES (?, ?, ?, ?, ?)";

	private static final String UPDATE_USER_INFO = "UPDATE users SET email = ?, password = ?, first_name = ?, last_name = ? WHERE id = ?";

	private static final String GET_ALL_USERS_BY_ROLE_ID = "SELECT * FROM users WHERE role_id = ?";

	private static final String GET_ALL_USERS_BY_STATUS_AND_ROLE_ID = "SELECT * FROM users WHERE status_id = ? AND role_id = ?";

	private static final String UPDATE_USER_STATUS = "UPDATE users SET status_id = ? WHERE id = ?";

	private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";

	private static final String GET_ALL_EMAIL = "SELECT email FROM users";

	private DAOFactory dataSource = DAOFactory.getInstance();


	@Override
	public void saveUser(User user) throws DAOException {

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			LOGGER.trace("Open Connection");
			connection = dataSource.getConnection();
			LOGGER.trace("Create PreparedStatement");
			statement = connection.prepareStatement(SAVE_USER);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getLastName());
			statement.executeUpdate();
			connection.commit();
			LOGGER.info("New user with email " + user.getEmail() + " successfully saved in the database");
		} catch (SQLException e) {
			LOGGER.error("Failed to save new user to database", e);
			try {
				connection.rollback();
				LOGGER.trace("Transaction is being rolled back");
			} catch (SQLException e1) {
				LOGGER.error("Rollback is failed", e1);
			}
			throw new DAOException("Failed to save new user to database", e);
		} finally {
			if(statement != null) {
				try {
					statement.close();
					LOGGER.trace("PreparedStatement is closed");
				} catch (SQLException e) {
					LOGGER.error("Failed to close PreparedStatement", e);
				}
			}
			if(connection != null) {
				try {
					connection.close();
					LOGGER.trace("Connection is closed");
				} catch (SQLException e) {
					LOGGER.error("Failed to close Connection", e);
				}
			}
		}
	}

	@Override
	public User getUserByEmailAndPassword(String email, String password) throws DAOException {

		User user = new User();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			LOGGER.trace("Open Connection");
			connection = dataSource.getConnection();
			LOGGER.trace("Create PreparedStatement");
			statement = connection.prepareStatement(GET_USER_BY_EMAIL_AND_PASSWORD);
			statement.setString(1, email);
			statement.setString(2, password);
			LOGGER.trace("Get ResultSet");
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				user.setId(resultSet.getInt("id"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setRoleId(resultSet.getInt("role_id"));
				user.setStatus(resultSet.getInt("status_id"));
			}
			connection.commit();
			LOGGER.info("User with email " + email + " successfully exctacted from the database");
		} catch(SQLException e) {
			LOGGER.error("Failed to exctract user with email " + email + " and password " + password + " from the database", e);
			try {
				connection.rollback();
				LOGGER.trace("Transaction is being rolled back");
			} catch (SQLException e1) {
				LOGGER.error("Rollback is failed", e1);
			}
			throw new DAOException("Failed to exctract user with email " + email + " and password " + password + " from the database", e);
		} finally {
			if(resultSet != null) {
				try {
					resultSet.close();
					LOGGER.trace("ResultSet is closed");
				} catch(SQLException e) {
					LOGGER.error("Failed to close ResultSet");
				}
			}
			if(statement != null) {
				try {
					statement.close();
					LOGGER.trace("PreparedStatement is closed");
				} catch (SQLException e) {
					LOGGER.error("Failed to close PreparedStatement", e);
				}
			}
			if(connection != null) {
				try {
					connection.close();
					LOGGER.trace("Connection is closed");
				} catch (SQLException e) {
					LOGGER.error("Failed to close Connection", e);
				}
			}
		}
		return user;
	}

	@Override
	public void saveTeacher(User user) throws DAOException {

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			LOGGER.trace("Open Connection");
			connection = dataSource.getConnection();
			LOGGER.trace("Create PreparedStatement");
			statement = connection.prepareStatement(SAVE_TEACHER);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getLastName());
			statement.setInt(5, user.getRoleId());
			statement.executeUpdate();
			connection.commit();
			LOGGER.info("New user with email " + user.getEmail() + " successfully saved in the database");
		} catch (SQLException e) {
			LOGGER.error("Failed to save new user to database", e);
			try {
				connection.rollback();
				LOGGER.trace("Transaction is being rolled back");
			} catch (SQLException e1) {
				LOGGER.error("Rollback is failed", e1);
			}
			throw new DAOException("Failed to save new user to database", e);
		} finally {
			if(statement != null) {
				try {
					statement.close();
					LOGGER.trace("PreparedStatement is closed");
				} catch (SQLException e) {
					LOGGER.error("Failed to close PreparedStatement", e);
				}
			}
			if(connection != null) {
				try {
					connection.close();
					LOGGER.trace("Connection is closed");
				} catch (SQLException e) {
					LOGGER.error("Failed to close Connection", e);
				}
			}
		}
	}

	@Override
	public void updateUserInfo(User user) throws DAOException {

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			LOGGER.trace("Open Connection");
			connection = dataSource.getConnection();
			LOGGER.trace("Create PreparedStatement");
			statement = connection.prepareStatement(UPDATE_USER_INFO);
			statement.setString(1, user.getEmail());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getLastName());
			statement.setInt(5, user.getId());
			statement.executeUpdate();
			connection.commit();
			LOGGER.info("User info successfully updated in the database");
		} catch (SQLException e) {
			LOGGER.error("Failed to update user info", e);
			try {
				connection.rollback();
				LOGGER.trace("Transaction is being rolled back");
			} catch (SQLException e1) {
				LOGGER.error("Rollback is failed", e1);
			}
			throw new DAOException("Failed to update user info", e);
		} finally {
			if(statement != null) {
				try {
					statement.close();
					LOGGER.trace("PreparedStatement is closed");
				} catch (SQLException e) {
					LOGGER.error("Failed to close PreparedStatement", e);
				}
			}
			if(connection != null) {
				try {
					connection.close();
					LOGGER.trace("Connection is closed");
				} catch (SQLException e) {
					LOGGER.error("Failed to close Connection", e);
				}
			}
		}
	}

	@Override
	public List<User> getAllUsersByRoleId(int roleId) throws DAOException {

		List<User> usersList = new ArrayList<User>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			LOGGER.trace("Open Connection");
			connection = dataSource.getConnection();
			LOGGER.trace("Create PreparedStatement");
			statement = connection.prepareStatement(GET_ALL_USERS_BY_ROLE_ID);
			statement.setInt(1, roleId);
			LOGGER.trace("Get ResultSet");
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setRoleId(resultSet.getInt("role_id"));
				usersList.add(user);
			}
			connection.commit();
			LOGGER.info("All users with roleId " + roleId + " successfully extacted from the database");
		} catch (SQLException e) {
			LOGGER.error("Failed to exctract all users with roleId " + roleId + " from the database", e);
			try {
				connection.rollback();
				LOGGER.trace("Transaction is being rolled back");
			} catch (SQLException e1) {
				LOGGER.error("Rollback is failed", e1);
			}
			throw new DAOException("Failed to exctract all users with roleId " + roleId + " from the database", e);
		} finally {
			if(resultSet != null) {
				try {
					resultSet.close();
					LOGGER.trace("ResultSet is closed");
				} catch(SQLException e) {
					LOGGER.error("Failed to close ResultSet");
				}
			}
			if(statement != null) {
				try {
					statement.close();
					LOGGER.trace("PreparedStatement is closed");
				} catch (SQLException e) {
					LOGGER.error("Failed to close PreparedStatement", e);
				}
			}
			if(connection != null) {
				try {
					connection.close();
					LOGGER.trace("Connection is closed");
				} catch (SQLException e) {
					LOGGER.error("Failed to close Connection", e);
				}
			}
		}
		return usersList;
	}

	@Override
	public List<User> getAllUsersByStatusAndRoleId(int isActive, int roleId) throws DAOException {

		List<User> usersList = new ArrayList<User>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			LOGGER.trace("Open Connection");
			connection = dataSource.getConnection();
			LOGGER.trace("Create PreparedStatement");
			statement = connection.prepareStatement(GET_ALL_USERS_BY_STATUS_AND_ROLE_ID);
			statement.setInt(1, isActive);
			statement.setInt(2, roleId);
			LOGGER.trace("Get ResultSet");
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				usersList.add(user);
			}
			connection.commit();
			LOGGER.info("All users with status " + isActive + " and roleId " + roleId
					+ " successfully extacted from the database");
		} catch (SQLException e) {
			LOGGER.error("Failed to exctract all users with status " + isActive + " and roleId " + roleId
					+ " from the database", e);
			try {
				connection.rollback();
				LOGGER.trace("Transaction is being rolled back");
			} catch (SQLException e1) {
				LOGGER.error("Rollback is failed", e1);
			}
			throw new DAOException("Failed to exctract all users with status " + isActive + " and roleId " + roleId
					+ " from the database", e);
		} finally {
			if(resultSet != null) {
				try {
					resultSet.close();
					LOGGER.trace("ResultSet is closed");
				} catch(SQLException e) {
					LOGGER.error("Failed to close ResultSet");
				}
			}
			if(statement != null) {
				try {
					statement.close();
					LOGGER.trace("PreparedStatement is closed");
				} catch (SQLException e) {
					LOGGER.error("Failed to close PreparedStatement", e);
				}
			}
			if(connection != null) {
				try {
					connection.close();
					LOGGER.trace("Connection is closed");
				} catch (SQLException e) {
					LOGGER.error("Failed to close Connection", e);
				}
			}
		}
		return usersList;
	}

	@Override
	public void updateUserStatus(User user) throws DAOException {

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			LOGGER.trace("Open Connection");
			connection = dataSource.getConnection();
			LOGGER.trace("Create PreparedStatement");
			statement = connection.prepareStatement(UPDATE_USER_STATUS);
			statement.setInt(1, user.getStatus());
			statement.setInt(2, user.getId());
			statement.executeUpdate();
			connection.commit();
			LOGGER.info("User status successfully updated in the database");
		} catch (SQLException e) {
			LOGGER.error("Failed to update user status", e);
			try {
				connection.rollback();
				LOGGER.trace("Transaction is being rolled back");
			} catch (SQLException e1) {
				LOGGER.error("Rollback is failed", e1);
			}
			throw new DAOException("Failed to update user status", e);
		} finally {
			if(statement != null) {
				try {
					statement.close();
					LOGGER.trace("PreparedStatement is closed");
				} catch (SQLException e) {
					LOGGER.error("Failed to close PreparedStatement", e);
				}
			}
			if(connection != null) {
				try {
					connection.close();
					LOGGER.trace("Connection is closed");
				} catch (SQLException e) {
					LOGGER.error("Failed to close Connection", e);
				}
			}
		}
	}

	@Override
	public User getUserById(int userId) throws DAOException {

		User user = new User();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			LOGGER.trace("Open Connection");
			connection = dataSource.getConnection();
			LOGGER.trace("Create PreparedStatement");
			statement = connection.prepareStatement(GET_USER_BY_ID);
			statement.setInt(1,userId);
			LOGGER.trace("Get ResultSet");
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				user.setId(resultSet.getInt("id"));
				user.setEmail(resultSet.getString("email"));
				user.setPassword(resultSet.getString("password"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setRoleId(resultSet.getInt("role_id"));
				user.setStatus(resultSet.getInt("status_id"));
			}
			connection.commit();
			LOGGER.info("User with id " + userId + " successfully exctacted from the database");
		} catch(SQLException e) {
			LOGGER.error("Failed to exctract user with id " + userId + " from the database", e);
			try {
				connection.rollback();
				LOGGER.trace("Transaction is being rolled back");
			} catch (SQLException e1) {
				LOGGER.error("Rollback is failed", e1);
			}
			throw new DAOException("Failed to exctract user with id " + userId + " from the database", e);
		} finally {
			if(resultSet != null) {
				try {
					resultSet.close();
					LOGGER.trace("ResultSet is closed");
				} catch(SQLException e) {
					LOGGER.error("Failed to close ResultSet");
				}
			}
			if(statement != null) {
				try {
					statement.close();
					LOGGER.trace("PreparedStatement is closed");
				} catch (SQLException e) {
					LOGGER.error("Failed to close PreparedStatement", e);
				}
			}
			if(connection != null) {
				try {
					connection.close();
					LOGGER.trace("Connection is closed");
				} catch (SQLException e) {
					LOGGER.error("Failed to close Connection", e);
				}
			}
		}
		return user;
	}

	@Override
	public List<String> getAllEmail() throws DAOException {
		List<String> emailList = new ArrayList<String>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			LOGGER.trace("Open Connection");
			connection = dataSource.getConnection();
			LOGGER.trace("Create PreparedStatement");
			statement = connection.prepareStatement(GET_ALL_EMAIL);
			LOGGER.trace("Get ResultSet");
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				emailList.add((resultSet.getString("email")));
			}
			connection.commit();
			LOGGER.info("All email successfully extacted from the database");
		} catch (SQLException e) {
			LOGGER.error("Failed to exctract all email from the database", e);
			try {
				connection.rollback();
				LOGGER.trace("Transaction is being rolled back");
			} catch (SQLException e1) {
				LOGGER.error("Rollback is failed", e1);
			}
			throw new DAOException("Failed to exctract all email from the database", e);
		} finally {
			if(resultSet != null) {
				try {
					resultSet.close();
					LOGGER.trace("ResultSet is closed");
				} catch(SQLException e) {
					LOGGER.error("Failed to close ResultSet");
				}
			}
			if(statement != null) {
				try {
					statement.close();
					LOGGER.trace("PreparedStatement is closed");
				} catch (SQLException e) {
					LOGGER.error("Failed to close PreparedStatement", e);
				}
			}
			if(connection != null) {
				try {
					connection.close();
					LOGGER.trace("Connection is closed");
				} catch (SQLException e) {
					LOGGER.error("Failed to close Connection", e);
				}
			}
		}
		return emailList;
	}
}
