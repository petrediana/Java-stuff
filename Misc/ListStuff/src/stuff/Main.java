package stuff;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	private final static String fileName1 = "File1.csv";
	private final static String fileName2 = "File2.csv";

	public static void main(String[] args) {
		Main app = new Main();

		try {
			List<Student> firtStudList = new ArrayList<>();
			List<Student> secondStudList = new ArrayList<>();

			app.readTxtFile(fileName1, firtStudList);
			app.readTxtFile(fileName2, secondStudList);

			System.out.println("\n---First list of students---\n");
			app.printList(firtStudList);

			System.out.println("\n---Second list of students---\n");
			app.printList(secondStudList);

			Group group1 = new Group(1, firtStudList);
			Group group2 = new Group(2, secondStudList);

			System.out.println("\n---Print the groups---\n");
			System.out.println(group1);
			System.out.println(group2);

			System.out.println("\n---Test equals for Group class---\n");
			app.testListEquals(group1, group1); //

			System.out.println("\n---Test clone for Group class---\n");
			Group group3 = (Group) group1.clone();
			group3.setId(3);
			group1.getStudents().add(new Student());

			System.out.println("Group 3 = \n" + group3);
			System.out.println("\nGroup 1 (added new stud) = \n" + group1);


			List<Group> groups = new ArrayList<>();
			groups.add(group3);
			groups.add(group1);
			groups.add(group2);

			System.out.println("\n---Added a list of groups---\n");
			groups.forEach(System.out::println);

			groups.sort(Group::compareTo);
			System.out.println("\n---Sorted the list by the group's id---\n");
			groups.forEach(System.out::println);

		}

		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	private void readTxtFile(String fileName, List<Student> list) {
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
			bufferedReader.lines().forEach(line->{
				String[] elements = line.split(",");

				Student student = new Student();
				student.setId(Integer.parseInt(elements[0].trim()));
				student.setName(elements[1].trim());
				student.setFinalGrade(Double.parseDouble(elements[2].trim()));

				list.add(student);
			});
		}

		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	private void printList(List<Student> list) {
		list.forEach(System.out::println);
	}

	public void testListEquals(Group group1, Group group2) {
		if(group1.equals(group2)){
			System.out.println("The groups are equal | Same id is shared: " + group1.getId() + " " + group2.getId());
		}
		else {
			System.out.println("The groups are not equal");
		}
	}
}
