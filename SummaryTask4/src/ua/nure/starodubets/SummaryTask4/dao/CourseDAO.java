package ua.nure.starodubets.SummaryTask4.dao;

import java.util.List;

import ua.nure.starodubets.SummaryTask4.model.Course;

/**
 *
 * @author Ihor Starodubets
 *
 */
public interface CourseDAO {

	/**
	 * Method saves new course to database
	 *
	 * @param course Course object
	 * @throws DAOException
	 */
	void saveCourse(Course course) throws DAOException;

	/**
	 * Method returns all courses
	 *
	 * @return list of courses
	 * @throws DAOException
	 */
	List<Course> getAllCourse() throws DAOException;

	/**
	 * Method updates course info
	 *
	 * @param course Course object
	 * @throws DAOException
	 */
	void updateCourseInfo(Course course) throws DAOException;

	/**
	 * Method deletes course by ID
	 *
	 * @param courseId courseId
	 * @throws DAOException
	 */
	void deleteCourseById(int courseId) throws DAOException;

	/**
	 * Method returns list of courses ordered by name ASC
	 *
	 * @return list of courses
	 * @throws DAOException
	 */
	List<Course> getAllCourseOrderByNameAsc() throws DAOException;

	/**
	 * Method returns list of courses ordered by name DESC
	 *
	 * @return list of courses
	 * @throws DAOException
	 */
	List<Course> getAllCourseOrderByNameDesc() throws DAOException;

	/**
	 * Method returns list of courses ordered by duration
	 *
	 * @return list of courses
	 * @throws DAOException
	 */
	List<Course> getAllCourseOrderByDuration() throws DAOException;

	/**
	 * Method returns list of courses ordered by number of student
	 *
	 * @return list of courses
	 * @throws DAOException
	 */
	List<Course> getAllCourseOrderByNumberStudents() throws DAOException;

	/**
	 * Method returns list of courses by teacherId
	 *
	 * @param teacherId teacher ID
	 * @return list of courses
	 * @throws DAOException
	 */
	List<Course> getAllCourseByTeacherId(int teacherId) throws DAOException;

	/**
	 * Method returns list of courses by subjectId
	 *
	 * @param subjectId subject ID
	 * @return list of courses
	 * @throws DAOException
	 */
	List<Course> getAllCourseBySubjectId(int subjectId) throws DAOException;

	/**
	 * Method updates number of student on the course
	 *
	 * @param courseId course ID
	 * @throws DAOException
	 */
	void updateNumberStudentsByCourseId(int courseId) throws DAOException;

	/**
	 * Method returns course by ID
	 *
	 * @param courseId course ID
	 * @return Course object
	 * @throws DAOException
	 */
	Course getCourseById(int courseId) throws DAOException;
}
