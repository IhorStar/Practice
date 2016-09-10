package ua.nure.starodubets.SummaryTask4.model;

/**
 * Subject entity for 'subjects' table
 *
 * @author Ihor Starodubets
 *
 */
public class Subject {

	private int id;
	private String name;

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

	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + "]";
	}
}
