package ua.nure.starodubets.SummaryTask4.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.starodubets.SummaryTask4.dao.CourseDAO;
import ua.nure.starodubets.SummaryTask4.dao.DAOException;
import ua.nure.starodubets.SummaryTask4.dao.DAOFactory;
import ua.nure.starodubets.SummaryTask4.model.Course;

/**
 * Class manipulates table courses
 *
 * @author Ihor Starodubets
 *
 */
public class CourseDAOImpl implements CourseDAO {

	private static final Logger LOGGER = Logger.getLogger(CourseDAOImpl.class);

	private static final String SAVE_COURSE = "INSERT INTO courses (name, course_start_date, course_end_date, duration, "
			+ "subject_id, teacher_id) VALUES (?, ?, ?, ?, ?, ?)";

	private static final String GET_ALL_COURSES = "SELECT * FROM courses";

	private static final String UPDATE_COURSE_INFO = "UPDATE courses SET course_start_date = ?, course_end_date = ?, "
			+ "duration = ?, teacher_id = ? WHERE id = ?";

	private static final String DELETE_COURSE_BY_ID = "DELETE FROM courses WHERE id = ?";

	private static final String GET_ALL_COURSES_ORDER_BY_NAME_ASC = "SELECT * FROM courses ORDER BY name ASC";

	private static final String GET_ALL_COURSES_ORDER_BY_NAME_DESC = "SELECT * FROM courses ORDER BY name DESC";

	private static final String GET_ALL_COURSES_ORDER_BY_DURATION = "SELECT * FROM courses ORDER BY duration ASC";

	private static final String GET_ALL_COURSES_ORDER_BY_NUMBER_STUDENTS = "SELECT * FROM courses ORDER BY number_students ASC";

	private static final String GET_ALL_COURSES_BY_TEACHER_ID = "SELECT * FROM courses WHERE teacher_id = ?";

	private static final String GET_ALL_COURSES_BY_SUBJECT_ID = "SELECT * FROM courses WHERE subject_id = ?";

	private static final String UPDATE_NUMBER_STUDENTS_BY_COURSE_ID = "UPDATE courses SET number_students = number_students + 1 WHERE id = ?";

	private static final String GET_COURSE_BY_ID = "SELECT * FROM courses WHERE id = ?";

	private DAOFactory dataSource = DAOFactory.getInstance();

	@Override
	public void saveCourse(Course course) throws DAOException {

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			LOGGER.trace("Open Connection");
			connection = dataSource.getConnection();
			LOGGER.trace("Create PreparedStatement");
			statement = connection.prepareStatement(SAVE_COURSE);
			statement.setString(1, course.getName());
			statement.setDate(2, course.getCourseStartDate());
			statement.setDate(3, course.getCourseEndDate());
			statement.setInt(4, course.getDuration());
			statement.setInt(5, course.getSubjectId());
			statement.setInt(6, course.getTeacherId());
			statement.executeUpdate();
			connection.commit();
			LOGGER.info("New course with name " + course.getName() + " successfully saved to the database");
		} catch (SQLException e) {
			LOGGER.error("Failed to save new course to database", e);
			try {
				connection.rollback();
				LOGGER.trace("Transaction is being rolled back");
			} catch (SQLException e1) {
				LOGGER.error("Rollback is failed", e1);
			}
			throw new DAOException("Failed to save new course to database", e);
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
	public List<Course> getAllCourse() throws DAOException {

		List<Course> coursesList = new ArrayList<Course>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			LOGGER.trace("Open Connection");
			connection = dataSource.getConnection();
			LOGGER.trace("Create PreparedStatement");
			statement = connection.prepareStatement(GET_ALL_COURSES);
			LOGGER.trace("Get ResultSet");
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Course course = new Course();
				course.setId(resultSet.getInt("id"));
				course.setName(resultSet.getString("name"));
				course.setCourseStartDate(resultSet.getDate("course_start_date"));
				course.setCourseEndDate(resultSet.getDate("course_end_date"));
				course.setDuration(resultSet.getInt("duration"));
				course.setNumberStudents(resultSet.getInt("number_students"));
				course.setSubjectId(resultSet.getInt("subject_id"));
				course.setTeacherId(resultSet.getInt("teacher_id"));
				coursesList.add(course);
			}
			connection.commit();
			LOGGER.info("All courses successfully extracted from the database");
		} catch (SQLException e) {
			LOGGER.error("Failed to exctract all courses from the database", e);
			try {
				connection.rollback();
				LOGGER.trace("Transaction is being rolled back");
			} catch (SQLException e1) {
				LOGGER.error("Rollback is failed", e1);
			}
			throw new DAOException("Failed to exctract all courses from the database", e);
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
		return coursesList;
	}

	@Override
	public void updateCourseInfo(Course course) throws DAOException {

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			LOGGER.trace("Open Connection");
			connection = dataSource.getConnection();
			LOGGER.trace("Create PreparedStatement");
			statement = connection.prepareStatement(UPDATE_COURSE_INFO);
			statement.setDate(1, course.getCourseStartDate());
			statement.setDate(2, course.getCourseEndDate());
			statement.setInt(3, course.getDuration());
			statement.setInt(4, course.getTeacherId());
			statement.setInt(5, course.getId());
			statement.executeUpdate();
			connection.commit();
			LOGGER.info("Course info successfully updated in the database");
		} catch (SQLException e) {
			LOGGER.error("Failed to update course info", e);
			try {
				connection.rollback();
				LOGGER.trace("Transaction is being rolled back");
			} catch (SQLException e1) {
				LOGGER.error("Rollback is failed", e1);
			}
			throw new DAOException("Failed to update course info", e);
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
	public void deleteCourseById(int courseId) throws DAOException {

		Connection connection = null;
		PreparedStatement statement = null;

		try {
			LOGGER.trace("Open Connection");
			connection = dataSource.getConnection();
			LOGGER.trace("Create PreparedStatement");
			statement = connection.prepareStatement(DELETE_COURSE_BY_ID);
			statement.setInt(1, courseId);
			statement.executeUpdate();
			connection.commit();
			LOGGER.info("Course successfully deleted from the database");
		} catch (SQLException e) {
			LOGGER.error("Failed to delete course", e);
			try {
				connection.rollback();
				LOGGER.trace("Transaction is being rolled back");
			} catch (SQLException e1) {
				LOGGER.error("Rollback is failed", e1);
			}
			throw new DAOException("Failed to delete course", e);
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
	public List<Course> getAllCourseOrderByNameAsc() throws DAOException {
		List<Course> coursesList = new ArrayList<Course>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			LOGGER.trace("Open Connection");
			connection = dataSource.getConnection();
			LOGGER.trace("Create PreparedStatement");
			statement = connection.prepareStatement(GET_ALL_COURSES_ORDER_BY_NAME_ASC);
			LOGGER.trace("Get ResultSet");
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Course course = new Course();
				course.setId(resultSet.getInt("id"));
				course.setName(resultSet.getString("name"));
				course.setCourseStartDate(resultSet.getDate("course_start_date"));
				course.setCourseEndDate(resultSet.getDate("course_end_date"));
				course.setDuration(resultSet.getInt("duration"));
				course.setNumberStudents(resultSet.getInt("number_students"));
				course.setSubjectId(resultSet.getInt("subject_id"));
				course.setTeacherId(resultSet.getInt("teacher_id"));
				coursesList.add(course);
			}
			connection.commit();
			LOGGER.info("All courses successfully extracted from the database");
		} catch (SQLException e) {
			LOGGER.error("Failed to exctract all courses from the database", e);
			try {
				connection.rollback();
				LOGGER.trace("Transaction is being rolled back");
			} catch (SQLException e1) {
				LOGGER.error("Rollback is failed", e1);
			}
			throw new DAOException("Failed to exctract all courses from the database", e);
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
		return coursesList;
	}

	@Override
	public List<Course> getAllCourseOrderByNameDesc() throws DAOException {
		List<Course> coursesList = new ArrayList<Course>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			LOGGER.trace("Open Connection");
			connection = dataSource.getConnection();
			LOGGER.trace("Create PreparedStatement");
			statement = connection.prepareStatement(GET_ALL_COURSES_ORDER_BY_NAME_DESC);
			LOGGER.trace("Get ResultSet");
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Course course = new Course();
				course.setId(resultSet.getInt("id"));
				course.setName(resultSet.getString("name"));
				course.setCourseStartDate(resultSet.getDate("course_start_date"));
				course.setCourseEndDate(resultSet.getDate("course_end_date"));
				course.setDuration(resultSet.getInt("duration"));
				course.setNumberStudents(resultSet.getInt("number_students"));
				course.setSubjectId(resultSet.getInt("subject_id"));
				course.setTeacherId(resultSet.getInt("teacher_id"));
				coursesList.add(course);
			}
			connection.commit();
			LOGGER.info("All courses successfully extracted from the database");
		} catch (SQLException e) {
			LOGGER.error("Failed to exctract all courses from the database", e);
			try {
				connection.rollback();
				LOGGER.trace("Transaction is being rolled back");
			} catch (SQLException e1) {
				LOGGER.error("Rollback is failed", e1);
			}
			throw new DAOException("Failed to exctract all courses from the database", e);
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
		return coursesList;
	}

	@Override
	public List<Course> getAllCourseOrderByDuration() throws DAOException {
		List<Course> coursesList = new ArrayList<Course>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			LOGGER.trace("Open Connection");
			connection = dataSource.getConnection();
			LOGGER.trace("Create PreparedStatement");
			statement = connection.prepareStatement(GET_ALL_COURSES_ORDER_BY_DURATION);
			LOGGER.trace("Get ResultSet");
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Course course = new Course();
				course.setId(resultSet.getInt("id"));
				course.setName(resultSet.getString("name"));
				course.setCourseStartDate(resultSet.getDate("course_start_date"));
				course.setCourseEndDate(resultSet.getDate("course_end_date"));
				course.setDuration(resultSet.getInt("duration"));
				course.setNumberStudents(resultSet.getInt("number_students"));
				course.setSubjectId(resultSet.getInt("subject_id"));
				course.setTeacherId(resultSet.getInt("teacher_id"));
				coursesList.add(course);
			}
			connection.commit();
			LOGGER.info("All courses successfully extracted from the database");
		} catch (SQLException e) {
			LOGGER.error("Failed to exctract all courses from the database", e);
			try {
				connection.rollback();
				LOGGER.trace("Transaction is being rolled back");
			} catch (SQLException e1) {
				LOGGER.error("Rollback is failed", e1);
			}
			throw new DAOException("Failed to exctract all courses from the database", e);
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
		return coursesList;
	}

	@Override
	public List<Course> getAllCourseOrderByNumberStudents() throws DAOException {
		List<Course> coursesList = new ArrayList<Course>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			LOGGER.trace("Open Connection");
			connection = dataSource.getConnection();
			LOGGER.trace("Create PreparedStatement");
			statement = connection.prepareStatement(GET_ALL_COURSES_ORDER_BY_NUMBER_STUDENTS);
			LOGGER.trace("Get ResultSet");
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Course course = new Course();
				course.setId(resultSet.getInt("id"));
				course.setName(resultSet.getString("name"));
				course.setCourseStartDate(resultSet.getDate("course_start_date"));
				course.setCourseEndDate(resultSet.getDate("course_end_date"));
				course.setDuration(resultSet.getInt("duration"));
				course.setNumberStudents(resultSet.getInt("number_students"));
				course.setSubjectId(resultSet.getInt("subject_id"));
				course.setTeacherId(resultSet.getInt("teacher_id"));
				coursesList.add(course);
			}
			connection.commit();
			LOGGER.info("All courses successfully extracted from the database");
		} catch (SQLException e) {
			LOGGER.error("Failed to exctract all courses from the database", e);
			try {
				connection.rollback();
				LOGGER.trace("Transaction is being rolled back");
			} catch (SQLException e1) {
				LOGGER.error("Rollback is failed", e1);
			}
			throw new DAOException("Failed to exctract all courses from the database", e);
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
		return coursesList;
	}

	@Override
	public List<Course> getAllCourseByTeacherId(int teacherId) throws DAOException {
		List<Course> coursesList = new ArrayList<Course>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			LOGGER.trace("Open Connection");
			connection = dataSource.getConnection();
			LOGGER.trace("Create PreparedStatement");
			statement = connection.prepareStatement(GET_ALL_COURSES_BY_TEACHER_ID);
			statement.setInt(1, teacherId);
			LOGGER.trace("Get ResultSet");
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Course course = new Course();
				course.setId(resultSet.getInt("id"));
				course.setName(resultSet.getString("name"));
				course.setCourseStartDate(resultSet.getDate("course_start_date"));
				course.setCourseEndDate(resultSet.getDate("course_end_date"));
				course.setDuration(resultSet.getInt("duration"));
				course.setNumberStudents(resultSet.getInt("number_students"));
				course.setSubjectId(resultSet.getInt("subject_id"));
				course.setTeacherId(resultSet.getInt("teacher_id"));
				coursesList.add(course);
			}
			connection.commit();
			LOGGER.info("All courses successfully extracted from the database");
		} catch (SQLException e) {
			LOGGER.error("Failed to exctract all courses from the database", e);
			try {
				connection.rollback();
				LOGGER.trace("Transaction is being rolled back");
			} catch (SQLException e1) {
				LOGGER.error("Rollback is failed", e1);
			}
			throw new DAOException("Failed to exctract all courses from the database", e);
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
		return coursesList;
	}

	@Override
	public List<Course> getAllCourseBySubjectId(int subjectId) throws DAOException {
		List<Course> coursesList = new ArrayList<Course>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			LOGGER.trace("Open Connection");
			connection = dataSource.getConnection();
			LOGGER.trace("Create PreparedStatement");
			statement = connection.prepareStatement(GET_ALL_COURSES_BY_SUBJECT_ID);
			statement.setInt(1, subjectId);
			LOGGER.trace("Get ResultSet");
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				Course course = new Course();
				course.setId(resultSet.getInt("id"));
				course.setName(resultSet.getString("name"));
				course.setCourseStartDate(resultSet.getDate("course_start_date"));
				course.setCourseEndDate(resultSet.getDate("course_end_date"));
				course.setDuration(resultSet.getInt("duration"));
				course.setNumberStudents(resultSet.getInt("number_students"));
				course.setSubjectId(resultSet.getInt("subject_id"));
				course.setTeacherId(resultSet.getInt("teacher_id"));
				coursesList.add(course);
			}
			connection.commit();
			LOGGER.info("All courses successfully extracted from the database");
		} catch (SQLException e) {
			LOGGER.error("Failed to exctract all courses from the database", e);
			try {
				connection.rollback();
				LOGGER.trace("Transaction is being rolled back");
			} catch (SQLException e1) {
				LOGGER.error("Rollback is failed", e1);
			}
			throw new DAOException("Failed to exctract all courses from the database", e);
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
		return coursesList;
	}

	@Override
	public void updateNumberStudentsByCourseId(int courseId) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;

		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(UPDATE_NUMBER_STUDENTS_BY_COURSE_ID);
			statement.setInt(1, courseId);
			statement.executeUpdate();
			connection.commit();
			LOGGER.info("Number of students for course " + courseId + " successfully updated in the database");
		} catch (SQLException e) {
			LOGGER.error("Failed to update number of students for course " + courseId, e);
			try {
				connection.rollback();
				LOGGER.trace("Transaction is being rolled back");
			} catch (SQLException e1) {
				LOGGER.error("Rollback is failed", e1);
			}
			throw new DAOException("Failed to update number of students for course " + courseId, e);
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
	public Course getCourseById(int courseId) throws DAOException {
		Course course = new Course();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			LOGGER.trace("Open Connection");
			connection = dataSource.getConnection();
			LOGGER.trace("Create PreparedStatement");
			statement = connection.prepareStatement(GET_COURSE_BY_ID);
			statement.setInt(1, courseId);
			LOGGER.trace("Get ResultSet");
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				course.setId(resultSet.getInt("id"));
				course.setName(resultSet.getString("name"));
				course.setCourseStartDate(resultSet.getDate("course_start_date"));
				course.setCourseEndDate(resultSet.getDate("course_end_date"));
				course.setDuration(resultSet.getInt("duration"));
				course.setNumberStudents(resultSet.getInt("number_students"));
				course.setSubjectId(resultSet.getInt("subject_id"));
				course.setTeacherId(resultSet.getInt("teacher_id"));
			}
			connection.commit();
			LOGGER.info("Course with id = " + courseId + " successfully extracted from the database");
		} catch (SQLException e) {
			LOGGER.error("Failed to exctract course with id = " + courseId + " from the database", e);
			try {
				connection.rollback();
				LOGGER.trace("Transaction is being rolled back");
			} catch (SQLException e1) {
				LOGGER.error("Rollback is failed", e1);
			}
			throw new DAOException("Failed to exctract course with id = " + courseId + " from the database", e);
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
		return course;
	}
}
