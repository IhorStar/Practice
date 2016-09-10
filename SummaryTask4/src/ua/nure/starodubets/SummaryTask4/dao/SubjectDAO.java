package ua.nure.starodubets.SummaryTask4.dao;

import java.util.List;

import ua.nure.starodubets.SummaryTask4.model.Subject;

/**
 *
 * @author Ihor Starodubets
 *
 */
public interface SubjectDAO {

	/**
	 * Method save new subject to database
	 *
	 * @param subject Subject object
	 * @throws DAOException
	 */
	void saveSubject(Subject subject) throws DAOException;

	/**
	 * Method returns all subjects from table subjects
	 *
	 * @return list of subjects
	 * @throws DAOException
	 */
	List<Subject> getAllSubjects() throws DAOException;
}
