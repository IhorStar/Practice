package ua.nure.starodubets.SummaryTask4.model;

import java.sql.Date;

/**
 * Course entity for 'courses' table
 *
 * @author Ihor Starodubets
 *
 */
public class Course {

	private int id;
	private String name;
	private Date courseStartDate;
	private Date courseEndDate;
	private int duration;
	private int numberStudents;
	private int subjectId;
	private int teacherId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCourseStartDate() {
		return courseStartDate;
	}

	public void setCourseStartDate(Date courseStartDate) {
		this.courseStartDate = courseStartDate;
	}

	public Date getCourseEndDate() {
		return courseEndDate;
	}

	public void setCourseEndDate(Date courseEndDate) {
		this.courseEndDate = courseEndDate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getNumberStudents() {
		return numberStudents;
	}

	public void setNumberStudents(int numberStudents) {
		this.numberStudents = numberStudents;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", courseStartDate=" + courseStartDate + ", courseEndDate="
				+ courseEndDate + ", duration=" + duration + ", numberStudents=" + numberStudents + ", subjectId="
				+ subjectId + ", teacherId=" + teacherId + "]";
	}
}
