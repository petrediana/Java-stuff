package srv;

import prj00DB.Student;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainServer {
	List<Student> suStudentsList = new ArrayList<>();

	public static void main(String[] args) {
		MainServer app = new MainServer();

		try {
			app.setSuStudentsList(app.readDataFromDbTable());
			app.printList();

			System.out.println("\nServer doing something...\n");
			app.startServerAndDoStuff();
		}

		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	public List<Student> getSuStudentsList() {
		return suStudentsList;
	}

	public void setSuStudentsList(List<Student> suStudentsList) {
		this.suStudentsList = suStudentsList;
	}

	List<Student> readDataFromDbTable() {
		List<Student> list = new ArrayList<>();

		String connectionString = "jdbc:derby:db/MYSAMPLE";
		try (Connection connection = DriverManager.getConnection(connectionString, "app", "app");
			 Statement selectStatement = connection.createStatement();
			 ResultSet selectResultSet = selectStatement.executeQuery("SELECT * FROM STUDENTS")) {

			System.out.println("Connected to the database!");

			while (selectResultSet.next()) {
				Student student = new Student();
				student.setId(selectResultSet.getInt(1));
				student.setName(selectResultSet.getString(2));

				String[] grades = selectResultSet.getString(3).split(" ");
				double[] studentGrades = new double[grades.length];
				for (int i = 0; i < grades.length; i++)
					studentGrades[i] = Double.parseDouble(grades[i]);
				student.setGrades(studentGrades);

				list.add(student);
			}
		}

		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}

		return list;
	}

	public void printList() {
		suStudentsList.forEach(System.out::println);
	}

	public void startServerAndDoStuff() {
		try (ServerSocket serverSocket = new ServerSocket(2013)) {
			serverSocket.setSoTimeout(10000);

			Socket socket = serverSocket.accept();
			Thread thread = new Thread(() -> processStuff(socket));
			thread.start();
		}

		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	public synchronized void processStuff(Socket socket) {
		try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			 ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

			out.writeObject("This is a text that I am sending to the client");
			System.out.println("This is what the server got from the client: " + in.readObject().toString());

			out.writeObject("Another text that I am sending!");
			System.out.println("Received from Client: " + in.readObject().toString());

			//Going to receive a list of names from the Client
			//Check if the there is a student that matches the given name

			//receive the number of names from the client
			int numberOfStudents = Integer.parseInt(in.readObject().toString());
			System.out.println("\nServer received: " + numberOfStudents + " students");

			//recieve the list of names from the client
			List<String> names = new ArrayList<>();
			for (int i = 0; i < numberOfStudents; i++)
				names.add(in.readObject().toString());

			System.out.println("\n---Received from Client this list of names:");
			names.forEach(System.out::println);

			//Make a boolean array -> true: student with that name exists; false -> it does not exist
			boolean[] exits = new boolean[numberOfStudents];

			for (Student stud : suStudentsList) {
				for (int i = 0; i < numberOfStudents; i++) {
					if (stud.getName().equalsIgnoreCase(names.get(i))) {
						exits[i] = true;
						//System.out.println("ok");
						break;
					}
				}
			}

			//Give back to the client the boolean array
			for (int i = 0; i < exits.length; i++)
				out.writeObject(exits[i]);
		}

		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
}
