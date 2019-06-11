package prj02;

import prj00DB.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
	List<Student> studentsList = new ArrayList<>();

	public static void main(String[] args) {
		Main app = new Main();

		try {
			app.setStudentsList(app.readStudentsFromDataBase());
			app.showList();

			System.out.println("\n---USE A STREAM TO SORT THE LIST BY ID");
			app.sortListById();
			app.showList();

			System.out.println("\n---USE A STREAM TO SORT THE LIST BY NAME");
			app.sortListByName();
			app.showList();

			System.out.println("\n---FILTER LIST BY EVEN IDS AND SORT THE LIST BY THE IDS");
			app.filterListByEvenIds();

			System.out.println("\n--FILTER LIST BY ODD IDS AND SORT BY NAME");
			app.filterListByOddIds();
		}

		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}

	}

	public List<Student> getStudentsList() {
		return studentsList;
	}

	public void setStudentsList(List<Student> studentsList) {
		this.studentsList = studentsList;
	}

	List<Student> readStudentsFromDataBase() {
		List<Student> newList = new ArrayList<>();

		String connectionString = "jdbc:derby:db/MYSAMPLE";
		try (Connection connection = DriverManager.getConnection(connectionString, "app", "app")) {
			System.out.println("Connected succesfully!");
			System.out.println("\nMain is sleeping for 2 seconds...");
			System.out.println("\n---GOING TO READ LIST FROM DATABASE:");
			Thread.sleep(2000);

			String command = "SELECT * FROM STUDENTS";
			try (Statement selectStatement = connection.createStatement()) {
				try (ResultSet selectResultSet = selectStatement.executeQuery(command)) {
					while (selectResultSet.next()) {
						Student student = new Student();
						student.setId(selectResultSet.getInt(1));
						student.setName(selectResultSet.getString(2));

						String[] stringGrades = selectResultSet.getString(3).split(" ");
						double[] studentGrades = new double[stringGrades.length];
						for (int i = 0; i < stringGrades.length; i++) {
							studentGrades[i] = Double.parseDouble(stringGrades[i]);
						}
						student.setGrades(studentGrades);

						newList.add(student);
					}
				}
			}
		}

		catch (Exception ex) {
			System.err.println(ex);
		}

		return newList;
	}

	private void showList() {
		studentsList.forEach(System.out::println);
	}

	private void sortListById() {
		studentsList = studentsList.stream().sorted((stud1, stud2) -> stud1.getId() < stud2.getId() ? -1 : 1)
				.collect(Collectors.toList());
	}

	private void sortListByName() {
		studentsList = studentsList.stream().sorted((stud1, stud2) -> stud1.getName().compareTo(stud2.getName()))
				.collect(Collectors.toList());
	}

	private void filterListByEvenIds() {
		List<Student> newList = studentsList.stream().filter((stud1) -> (stud1.getId() % 2) == 0)
				.sorted((stud1, stud2) -> stud1.getId() < stud2.getId() ? -1 : 1).collect(Collectors.toList());

		newList.forEach(System.out::println);
	}

	private void filterListByOddIds() {
		List<Student> newList = studentsList.stream().filter((stud1) -> (stud1.getId() % 2 ) != 0)
				.sorted((stud1, stud2) -> stud1.getName().compareTo(stud2.getName())).collect(Collectors.toList());

		newList.forEach(System.out::println);
	}
}
