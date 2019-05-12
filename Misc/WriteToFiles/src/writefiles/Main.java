package writefiles;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main implements IO {
	private List<Student> students = new ArrayList<>();
	private final static String txtFile = "Students.csv";
	private final static String binaryFile = "Temp.dat";

	public static void main(String[] args) {
		Main app = new Main();

		try {
			System.out.println("\n---Read students from txt File---\n");
			app.readFromTxt(txtFile);
			app.printStudentList();

			System.out.println("\n---Write + Read students from binary File---\n");
			app.writeToBinary(binaryFile);
			app.readFromBinary(binaryFile);
			app.printStudentList();
		}

		catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void readFromTxt(String fileName) throws Exception {
		students.clear();
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){
			bufferedReader.lines().forEach(line->{
				String[] elements = line.split(",");
				Student student = new Student();

				student.setId(Integer.parseInt(elements[0].trim()));
				student.setName(elements[1].trim());
				student.setFinalGrade(Double.parseDouble(elements[2].trim()));

				students.add(student);
			});
		}

		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	@Override
	public void writeToBinary(String fileName) throws Exception {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))){
			for(Student student : students){
				oos.writeObject(student);
			}
		}

		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	@Override
	public void readFromBinary(String fileName) throws Exception {
		students.clear();
		try(FileInputStream fileInputStream = new FileInputStream(fileName);
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

			while(fileInputStream.available() != 0){
				students.add((Student) objectInputStream.readObject());
			}
		}

		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	private void printStudentList() {
		students.forEach(System.out::println);
	}
}
