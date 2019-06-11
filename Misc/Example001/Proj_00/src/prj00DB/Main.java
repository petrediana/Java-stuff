package prj00DB;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main implements IO {
	private static List<Student> students = new ArrayList<>();
	private static String binaryFileName = "Students.dat";

	public static void main(String[] args) {
		Main app = new Main();

		try {
			System.out.println("\n---READ FROM TXT FILE---");
			app.readFromTxtFile("Students.csv");
			app.showList();

			System.out.println("\n---WRITE LIST TO BINARY---");
			app.writeToBinaryFile(binaryFileName);

			System.out.println("\n---READ FROM BINARY");
			app.readFromBinaryFile(binaryFileName);
			app.showList();

		}

		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}

		String connectionString = "jdbc:derby://localhost:1527/MYSAMPLE;create=true";
		try (Connection connection = DriverManager.getConnection(connectionString, "app", "app")) {
			System.out.println("Connection established");

			/*//Delete table Students
			try (Statement dropStatement = connection.createStatement()) {
				String command = "DROP TABLE STUDENTS";
				dropStatement.executeUpdate(command);
			}
*/
			//Create table Students
			try (ResultSet resultSet = connection.getMetaData()
					.getTables(null, "app", "STUDENTS", new String[] {"TABLES"})) {

				if (!resultSet.next()) {
					String command = "CREATE TABLE STUDENTS (id integer, name varchar(100), grades varchar(100))";

					try (Statement createStatement = connection.createStatement()){
						createStatement.executeUpdate(command);
						System.out.println("Table was created!");
					}
				}
				else
					System.out.println("Table STUDENTS already exists!");
			}

			catch (Exception ex) {
				System.err.println(ex);
			}


			//First clear the data from the table to avoid duplicates
			try (Statement deleteStatement = connection.createStatement()) {
				String command = "DELETE FROM STUDENTS";
				deleteStatement.executeUpdate(command);
				System.out.println("\nData deleted to avoid duplicate values!");
			}

			//Put the values from the list into the table
			try (Statement insertStatement = connection.createStatement()) {
				System.out.println("\nInserting data from the list to the table...");
				for (Student stud : students) {
					String getGrades = "";
					for (int i = 0; i < stud.getGrades().length; i++)
						getGrades += stud.getGrades()[i] + " ";

					String command = "INSERT INTO STUDENTS(id, name, grades) VALUES ("
							+ stud.getId() + ",'" + stud.getName() + "','" + getGrades + "')";

					insertStatement.executeUpdate(command);
				}
			}

			//Print the values from the database table
			try (Statement selectStatement = connection.createStatement()) {
				String command = "SELECT * FROM STUDENTS";

				System.out.println("\n---PRINT DATA FROM THE TABLE:");
				try (ResultSet selectResultSet = selectStatement.executeQuery(command)) {
					while (selectResultSet.next()){

						System.out.println(selectResultSet.getInt(1) + " " +
						selectResultSet.getString(2) + " " + selectResultSet.getString(3));
					}
				}
			}

		}

		catch (Exception ex){
			System.err.println(ex);
		}
	}

	@Override
	public void readFromTxtFile(String fileName) throws Exception {
		students.clear();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
			bufferedReader.lines().forEach(line->{
				String[] values = line.split(",");

				Student student = new Student();
				student.setId(Integer.parseInt(values[0].trim()));
				student.setName(values[1].trim());

				int howManyGrades = values.length - 2;
				double[] grades = new double[howManyGrades];

				for (int i = 0; i < howManyGrades; i++)
					grades[i] = Double.parseDouble(values[2 + i].trim());
				student.setGrades(grades);

				students.add(student);
			});
		}

		catch (Exception ex){
			System.err.println(ex.getMessage());
		}
	}

	@Override
	public void writeToBinaryFile(String fileName) throws Exception {
		try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
			for (Student stud : students){
				objectOutputStream.writeObject(stud);
			}
		}

		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	@Override
	public void readFromBinaryFile(String fileName) throws Exception {
		students.clear();
		try (FileInputStream fileInputStream = new FileInputStream(fileName);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

			while (fileInputStream.available() != 0){
				students.add((Student) objectInputStream.readObject());
			}
		}

		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	public void showList(){
		students.forEach(System.out::println);
	}
}
