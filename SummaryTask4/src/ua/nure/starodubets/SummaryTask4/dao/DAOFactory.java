package ua.nure.starodubets.SummaryTask4.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import ua.nure.starodubets.SummaryTask4.dao.impl.CourseDAOImpl;
import ua.nure.starodubets.SummaryTask4.dao.impl.JournalDAOImpl;
import ua.nure.starodubets.SummaryTask4.dao.impl.SubjectDAOImpl;
import ua.nure.starodubets.SummaryTask4.dao.impl.UserDAOImpl;

/**
 * The class establishes connection to database
 *
 * @author Ihor Starodubets
 *
 */
public class DAOFactory {

	private static final Logger LOGGER = Logger.getLogger(DAOFactory.class);

	private static DAOFactory instance;
	private static DataSource dataSource;

	private DAOFactory() {}

	public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

	/**
	 * Method returns Connection
	 *
	 * @return Connection
	 * @throws DAOException
	 */
	public Connection getConnection() throws DAOException {

		Connection connection = null;

		if (dataSource == null) {
			try {
				Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:comp/env");
                dataSource = (DataSource) envContext.lookup("jdbc/summary_task4");
			} catch (NamingException e) {
                throw new DAOException("NamingException has occured", e);
            }
		}

		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			LOGGER.error("Cannot establish connection to database", e);
		}
		return connection;
    }

	/**
	 *
	 * @return UserDAOImpl object
	 */
	public UserDAO getUserDAO() {
		return new UserDAOImpl();
	}

	/**
	 *
	 * @return CourseDAOImpl object
	 */
	public CourseDAO getCourseDAO() {
		return new CourseDAOImpl();
	}

	/**
	 *
	 * @return SubjectDAOImpl object
	 */
	public SubjectDAO getSubjectDAO() {
		return new SubjectDAOImpl();
	}

	/**
	 *
	 * @return JournalDAOImpl object
	 */
	public JournalDAO getJournalDAO() {
		return new JournalDAOImpl();
	}
}
