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
import ua.nure.starodubets.SummaryTask4.dao.JournalDAO;
import ua.nure.starodubets.SummaryTask4.model.Journal;

/**
 * Class manipulates table journals
 *
 * @author Ihor Starodubets
 *
 */
public class JournalDAOImpl implements JournalDAO {

	private static final Logger LOGGER = Logger.getLogger(JournalDAOImpl.class);

	private static final String SAVE_JOURNAL = "INSERT INTO journals (course_id, teacher_id, student_id) VALUES (?, ?, ?)";

	private static final String GET_ALL_JOURNALS_BY_TEACHER_ID = "SELECT * FROM journals WHERE teacher_id = ?";

	private static final String GET_ALL_JOURNALS_BY_STUDENT_ID = "SELECT * FROM journals WHERE student_id = ?";

	private static final String GET_ALL_STUDENTS_ID_BY_COURSE_ID = "SELECT student_id FROM journals WHERE course_id = ?";

	private static final String GET_RATING_BY_STUDENT_ID_AND_COURSE_ID = "SELECT rating FROM journals WHERE student_id = ? AND course_id = ?";

	private static final String UPDATE_RATING = "UPDATE journals SET rating = ? WHERE student_id = ? AND course_id = ?";

	private static final String GET_JOURNAL_BY_STUDENT_ID_AND_COURSE_ID = "SELECT * FROM journals WHERE student_id = ? AND course_id = ?";

	DAOFactory dataSource = DAOFactory.getInstance();

	@Override
	public void saveJournal(Journal journal) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(SAVE_JOURNAL);
			statement.setInt(1, journal.getCourseId());
			statement.setInt(2, journal.getTeacherId());
			statement.setInt(3, journal.getStudentId());
			statement.executeUpdate();
			connection.commit();
			LOGGER.info("New journal successfully save to the database");
		} catch (SQLException e) {
			LOGGER.error("Failed to save new journal to the database", e);
			try {
				connection.rollback();
				LOGGER.trace("Transaction is being rolled back");
			} catch (SQLException e1) {
				LOGGER.error("Rollback is failed", e1);
			}
			throw new DAOException("Failed to save new journal to the database", e);
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
	public List<Journal> getAllJournalsByTeacherId(int teacherId) throws DAOException {
		List<Journal> journalsList = new ArrayList<Journal>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(GET_ALL_JOURNALS_BY_TEACHER_ID);
			statement.setInt(1, teacherId);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Journal journal = new Journal();
				journal.setId(resultSet.getInt("id"));
				journal.setCourseId(resultSet.getInt("course_id"));
				journal.setTeacherId(resultSet.getInt("teacher_id"));
				journal.setStudentId(resultSet.getInt("student_id"));
				journal.setRating(resultSet.getInt("rating"));
				journalsList.add(journal);
			}
			connection.commit();
			LOGGER.info("All journals with teacherId = " + teacherId + " successfully extracted from the database");
		} catch (SQLException e) {
			LOGGER.error("Failed to extract journals with teacherId = " + teacherId + " from the database", e);
			try {
				connection.rollback();
				LOGGER.trace("Transaction is being rolled back");
			} catch (SQLException e1) {
				LOGGER.error("Rollback is failed", e1);
			}
			throw new DAOException("Failed to extract journals with teacherId = " + teacherId + " from the database", e);
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
		return journalsList;
	}

	@Override
	public List<Journal> getAllJournalsByStudentId(int studentId) throws DAOException {
		List<Journal> journalsList = new ArrayList<Journal>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(GET_ALL_JOURNALS_BY_STUDENT_ID);
			statement.setInt(1, studentId);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Journal journal = new Journal();
				journal.setId(resultSet.getInt("id"));
				journal.setCourseId(resultSet.getInt("course_id"));
				journal.setTeacherId(resultSet.getInt("teacher_id"));
				journal.setStudentId(resultSet.getInt("student_id"));
				journal.setRating(resultSet.getInt("rating"));
				journalsList.add(journal);
			}
			connection.commit();
			LOGGER.info("All journals with studentId = " + studentId + " successfully extracted from the database");
		} catch (SQLException e) {
			LOGGER.error("Failed to extract journals with studentId = " + studentId + " from the database", e);
			try {
				connection.rollback();
				LOGGER.trace("Transaction is being rolled back");
			} catch (SQLException e1) {
				LOGGER.error("Rollback is failed", e1);
			}
			throw new DAOException("Failed to extract journals with studentId = " + studentId + " from the database", e);
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
		return journalsList;
	}

	@Override
	public List<Integer> getAllStudentsIdByCourseId(int courseId) throws DAOException {
		List<Integer> journalsList = new ArrayList<Integer>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(GET_ALL_STUDENTS_ID_BY_COURSE_ID);
			statement.setInt(1, courseId);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				journalsList.add(resultSet.getInt("student_id"));
			}
			connection.commit();
			LOGGER.info("All students with courseId = " + courseId + " successfully extracted from the database");
		} catch (SQLException e) {
			LOGGER.error("Failed to extract students with courseId = " + courseId + " from the database", e);
			try {
				connection.rollback();
				LOGGER.trace("Transaction is being rolled back");
			} catch (SQLException e1) {
				LOGGER.error("Rollback is failed", e1);
			}
			throw new DAOException("Failed to extract students with courseId = " + courseId + " from the database", e);
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
		return journalsList;
	}

	@Override
	public int getRatingByStudentIdAndCourseId(int studentId, int courseId) throws DAOException {
		int rating = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(GET_RATING_BY_STUDENT_ID_AND_COURSE_ID);
			statement.setInt(1, studentId);
			statement.setInt(2, courseId);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				rating = resultSet.getInt("rating");
			}
			connection.commit();
			LOGGER.info("Rating with studentId = " + studentId + " and  courseId = " + courseId
					+ " successfully extracted from the database");
		} catch (SQLException e) {
			LOGGER.error("Failed to extract rating with studentId = " + studentId + " and  courseId = " + courseId
					+ " from the database", e);
			try {
				connection.rollback();
				LOGGER.trace("Transaction is being rolled back");
			} catch (SQLException e1) {
				LOGGER.error("Rollback is failed", e1);
			}
			throw new DAOException("Failed to extract rating with studentId = " + studentId + " and  courseId = " + courseId
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
		return rating;
	}

	@Override
	public void updateRating(Journal journal) throws DAOException {

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(UPDATE_RATING);
			statement.setInt(1, journal.getRating());
			statement.setInt(2, journal.getStudentId());
			statement.setInt(3, journal.getCourseId());
			statement.executeUpdate();
			connection.commit();
			LOGGER.info("Rating successfully updated in the database");
		} catch (SQLException e) {
			LOGGER.error("Failed to update rating", e);
			try {
				connection.rollback();
				LOGGER.trace("Transaction is being rolled back");
			} catch (SQLException e1) {
				LOGGER.error("Rollback is failed", e1);
			}
			throw new DAOException("Failed to update rating", e);
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
	public Journal getJournalByStudentIdAndCourseId(int studentId, int courseId) throws DAOException {
		Journal journal = new Journal();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(GET_JOURNAL_BY_STUDENT_ID_AND_COURSE_ID);
			statement.setInt(1, studentId);
			statement.setInt(2, courseId);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				journal.setId(resultSet.getInt("id"));
				journal.setCourseId(resultSet.getInt("course_id"));
				journal.setTeacherId(resultSet.getInt("teacher_id"));
				journal.setStudentId(resultSet.getInt("student_id"));
				journal.setRating(resultSet.getInt("rating"));
			}
			connection.commit();
			LOGGER.info("Journal with studentId = " + studentId + " and courseId = " + courseId + " successfully extracted from the database");
		} catch (SQLException e) {
			LOGGER.error("Failed to extract journal with studentId = " + studentId + " and courseId = " + courseId + " from the database", e);
			try {
				connection.rollback();
				LOGGER.trace("Transaction is being rolled back");
			} catch (SQLException e1) {
				LOGGER.error("Rollback is failed", e1);
			}
			throw new DAOException("Failed to extract journal with studentId = " + studentId + " and courseId = " + courseId + " from the database", e);
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
		return journal;
	}
}
