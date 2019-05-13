package testing;

import writefiles.Main;
import writefiles.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AnotherMain {
	public static void main(String[] args) {
		Main jarMain = new Main();

		List<Student> studentList = new ArrayList<>();
		try {
			jarMain.readFromTxt("Students.csv");
			studentList = jarMain.getStudents();
			studentList.forEach(System.out::println);
			studentList.add(new Student(10, "Stud name5", 7.25));


			System.out.println("\n---Sort the list by id---\n");
			List<Student> sortedListByID = studentList.stream().sorted().collect(Collectors.toList());
			sortedListByID.forEach(System.out::println);

			System.out.println("\n---Sort the list by the final grade---\n");
			List<Student> sortedListbyGrades = studentList.stream().sorted((stud1, stud2)->
			stud1.getFinalGrade() < stud2.getFinalGrade() ? -1 : 1).collect(Collectors.toList());
			sortedListbyGrades.forEach(System.out::println);

			System.out.println("\n---Filter the list to have greater final grades than an input---\n");
			double maxGrade = 6.00;
			List<Student> filteredListByAGrade = studentList.stream().filter((stud1)->
			stud1.getFinalGrade() > maxGrade).collect(Collectors.toList());
			filteredListByAGrade.forEach(System.out::println);

			System.out.println("\n---Filter the list to have greater final grades than an input + sorted---\n");
			List<Student> filteredListByAGradeSorted = studentList.stream().filter((stud1)->
			stud1.getFinalGrade() > maxGrade).sorted((stud1, stud2) -> stud1.getFinalGrade() < stud2.getFinalGrade()
			? -1 : 1).collect(Collectors.toList());
			filteredListByAGradeSorted.forEach(System.out::println);

			System.out.println("\n---Get the list with the students name---\n");
			Set<String> studentNames = studentList.stream().map((stud1) -> stud1.getName()).collect(Collectors.toSet());
			studentNames.forEach(System.out::println);



		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
