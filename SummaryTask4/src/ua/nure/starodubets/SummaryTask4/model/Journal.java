package ua.nure.starodubets.SummaryTask4.model;

/**
 * Journal entity for 'journals' table
 *
 * @author Ihor Starodubets
 *
 */
public class Journal {

	private int id;
	private int courseId;
	private int teacherId;
	private int studentId;
	private int rating;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Journal [id=" + id + ", courseId=" + courseId + ", teacherId=" + teacherId + ", studentId=" + studentId
				+ ", rating=" + rating + "]";
	}
}
