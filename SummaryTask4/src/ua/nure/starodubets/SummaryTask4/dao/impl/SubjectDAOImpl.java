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
import ua.nure.starodubets.SummaryTask4.dao.SubjectDAO;
import ua.nure.starodubets.SummaryTask4.model.Subject;

/**
 * Class manipulates table subjects
 *
 * @author Ihor Starodubets
 *
 */
public class SubjectDAOImpl implements SubjectDAO {

	private static final Logger LOGGER = Logger.getLogger(SubjectDAOImpl.class);

	private static final String SAVE_SUBJECT = "INSERT INTO subjects (name) VALUES (?)";

	private static final String GET_ALL_SUBJECTS = "SELECT * FROM subjects";

	private DAOFactory dataSource = DAOFactory.getInstance();

	@Override
	public void saveSubject(Subject subject) throws DAOException {

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			LOGGER.trace("Open Connection");
			connection = dataSource.getConnection();
			LOGGER.trace("Create PreparedStatement");
			statement = connection.prepareStatement(SAVE_SUBJECT);
			statement.setString(1, subject.getName());
			statement.executeUpdate();
			connection.commit();
			LOGGER.info("New subject successfully saved in the database");
		} catch (SQLException e) {
			LOGGER.error("Failed to save new subject to database", e);
			try {
				connection.rollback();
				LOGGER.trace("Transaction is being rolled back");
			} catch (SQLException e1) {
				LOGGER.error("Rollback is failed", e1);
			}
			throw new DAOException("Failed to save new subject to database", e);
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
	public List<Subject> getAllSubjects() throws DAOException {

		List<Subject> subjectsList = new ArrayList<Subject>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			LOGGER.trace("Open Connection");
			connection = dataSource.getConnection();
			LOGGER.trace("Create PreparedStatement");
			statement = connection.prepareStatement(GET_ALL_SUBJECTS);
			LOGGER.trace("Get ResultSet");
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Subject subject = new Subject();
				subject.setId(resultSet.getInt("id"));
				subject.setName(resultSet.getString("name"));
				subjectsList.add(subject);
			}
			connection.commit();
			LOGGER.info("All subjects successfully extracted from the database");
		} catch (SQLException e) {
			LOGGER.error("Failed to exctract all subjects from the database", e);
			try {
				connection.rollback();
				LOGGER.trace("Transaction is being rolled back");
			} catch (SQLException e1) {
				LOGGER.error("Rollback is failed", e1);
			}
			throw new DAOException("Failed to exctract all subjects from the database", e);
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
		return subjectsList;
	}

}
