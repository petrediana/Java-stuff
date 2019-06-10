package dbDerby;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class MainClass {
	public static void main(String[] args) {
		System.out.println("Main starting...\n\n");
		String connectionString = "jdbc:derby://localhost:1527/sample;create=true";

		// Establish your connection by running the .bat startNetwork from derby-bin file
		try (Connection connection = DriverManager.getConnection(connectionString)){
			System.out.println("Connection established");

			//Check OR Create a table using ResultSet to get the data from the database
			try (ResultSet resultSet = connection.getMetaData().getTables(null, "app",
					"STUDENTS", new String[]{"TABLE"})) {

				if (!resultSet.next()) {
					// method next() returns false which means there is no current row available, so we need to create one
					try (Statement createTableStatement = connection.createStatement()) {
						createTableStatement.
								executeUpdate("CREATE TABLE STUDENTS (name varchar(50), id integer, grade double)");
						System.out.println("Table created!");

					} catch (Exception ex) {

						System.err.println(ex.getMessage());
						System.out.println("Something wrong while initializing the CREATE table statement");
						System.out.println("If table already exists IGNORE!\n");
					}
				}
			}
			
			//Delete first the data table to avoid duplicates when running the program
				try (Statement deleteStatement = connection.createStatement()){
					String command = "DELETE FROM STUDENTS";

					try {
						deleteStatement.executeUpdate(command);
					}

					catch (Exception ex){
						System.err.println(ex.getMessage());
						System.out.println("Something wrong at the DELETE statement");
					}
				}

			//Insert some data into students table
				try (Statement insertStatement = connection.createStatement()) {
					//Read from a .csv file the data
					try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Students.csv"))){
						bufferedReader.lines().forEach(line->{
							String[] datas = line.split(",");

							try {
								insertStatement.executeUpdate("INSERT INTO STUDENTS(NAME,ID,GRADE) VALUES('" +
										datas[0].trim() + "'," +
										Integer.parseInt(datas[1].trim()) + ", " +
										Double.parseDouble(datas[2].trim()) + ")");

								//System.out.println(datas[2].trim());
								//System.out.println("\n---Data inserted---\n");
							}

							catch (Exception ex){
								System.err.println(ex.getMessage());
								System.out.println("Something wrong while executing the insert!");
							}
						});
					}

				}

				catch (Exception ex){
					System.err.println(ex.getMessage());
					System.out.println("Something wrong with insert statement!");
				}

				//Let's get our data from the table
				try (Statement selectStatement = connection.createStatement()) {
					String command = "SELECT * FROM STUDENTS";

						try (ResultSet selectResultSet = selectStatement.executeQuery(command)) {
							while (selectResultSet.next())
						System.out.println("Name: " + selectResultSet.getString(1) +
						" ID: " + selectResultSet.getInt(2) + " GRADE: " + selectResultSet.getDouble(3));
						}
				}

				catch (Exception ex){
					System.err.println(ex.getMessage());
					System.out.println("Something wrong at SELECT *");
				}
		}

		catch(Exception ex){
			System.err.println(ex.getMessage());
			System.out.println("Cannot connect to the server...");
		}
	}
}
