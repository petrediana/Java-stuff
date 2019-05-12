package writefiles;

import java.io.Serializable;

public class Student implements Serializable {
	private int id;
	private String name;
	private double finalGrade;

	public Student() {}

	public Student(int id, String name, double finalGrade) {
		this.id = id;
		this.name = name;
		this.finalGrade = finalGrade;
	}

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

	public double getFinalGrade() {
		return finalGrade;
	}

	public void setFinalGrade(double finalGrade) {
		this.finalGrade = finalGrade;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", name='" + name + '\'' +
				", finalGrade=" + finalGrade +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Student student = (Student) o;

		return id == student.id;
	}

	@Override
	public int hashCode() {
		return id;
	}


}
