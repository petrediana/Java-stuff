package stuff;

import java.util.ArrayList;
import java.util.List;

public class Group implements Comparable<Group>, Cloneable{
	private int id;
	private List<Student> students = new ArrayList<>();

	public Group() {}

	public Group(int id, List<Student> students) {
		this.id = id;
		this.students = students;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Group{" +
				"id=" + id +
				", students=" + students +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Group group = (Group) o;

		return id == group.id;
	}

	@Override
	public int hashCode() {
		return id;
	}


	@Override
	public int compareTo(Group o) {
		return this.id < o.id ? -1 : 1;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Group clona = (Group) super.clone();
		List<Student> newList = new ArrayList<>();

		for(Student student : students) {
			Student newStud = new Student(student.getId(), student.getName(), student.getFinalGrade());
			newList.add(newStud);
			//newList.add((Student)student.clone());
		}

		clona.setStudents(newList);

		return clona;
	}

}
