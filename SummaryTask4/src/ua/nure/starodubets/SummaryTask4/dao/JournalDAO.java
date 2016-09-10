package ua.nure.starodubets.SummaryTask4.dao;

import java.util.List;

import ua.nure.starodubets.SummaryTask4.model.Journal;

/**
 *
 * @author Ihor Starodubets
 *
 */
public interface JournalDAO {

	/**
	 * Method saves new journal to table journals in database
	 *
	 * @param journal Journal object
	 * @throws DAOException
	 */
	void saveJournal(Journal journal) throws DAOException;

	/**
	 * Method returns all journals by teacherId from table journals in database
	 *
	 * @param teacherId teacher ID
	 * @return list of journals
	 * @throws DAOException
	 */
	List<Journal> getAllJournalsByTeacherId(int teacherId) throws DAOException;

	/**
	 * Method returns all journals by studentId from table journals in database
	 *
	 * @param studentId student ID
	 * @return list of journals
	 * @throws DAOException
	 */
	List<Journal> getAllJournalsByStudentId(int studentId) throws DAOException;

	/**
	 * Method returns all student which registered for course
	 *
	 * @param courseId course ID
	 * @return list of students ID
	 * @throws DAOException
	 */
	List<Integer> getAllStudentsIdByCourseId(int courseId) throws DAOException;

	/**
	 * Method returns student rating
	 *
	 * @param studentId student Id
	 * @param courseId course ID
	 * @return rating
	 * @throws DAOException
	 */
	int getRatingByStudentIdAndCourseId(int studentId, int courseId) throws DAOException;

	/**
	 * Method updates student rating
	 *
	 * @param journal Journal object
	 * @throws DAOException
	 */
	void updateRating(Journal journal) throws DAOException;

	/**
	 * Method returns journal by studentId and courseId
	 *
	 * @param studentId
	 * @param courseId
	 * @return
	 * @throws DAOException
	 */
	Journal getJournalByStudentIdAndCourseId(int studentId, int courseId) throws DAOException;
}
