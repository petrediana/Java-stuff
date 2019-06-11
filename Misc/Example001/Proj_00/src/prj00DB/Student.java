package prj00DB;


import java.io.Serializable;
import java.util.Arrays;

public class Student implements Serializable {
	private int id;
	private String name;
	private double[] grades;

	public Student() {}

	public Student(int id, String name, double[] grades) {
		this.id = id;
		this.name = name;
		this.grades = grades;
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

	public double[] getGrades() {
		return grades;
	}

	public void setGrades(double[] grades) {
		this.grades = grades;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", name=" + name +
				", grades=" + Arrays.toString(grades) +
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
