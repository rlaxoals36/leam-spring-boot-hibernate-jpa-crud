package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {

		return runner -> {
			// createStudent(studentDAO);

			createMultipleStudent(studentDAO);

			// readStudent(studentDAO);

			// queryForStudent(studentDAO);

			// queryForStudentByLastName(studentDAO);

			// updateStudent(studentDAO);

			// deleteStudent(studentDAO);

			//deleteAllStudent(studentDAO);
		};
	}

	private void deleteAllStudent(StudentDAO studentDAO) {

		System.out.println("Deleting all students");
		int numRowsDeleted = studentDAO.deleteAll();
		System.out.println("Deleted row count: " + numRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {

		int studentId = 3;
		System.out.println("Deleting student id: " + studentId);
		studentDAO.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {

		// retriev student based on the id: primary key
		int studentId = 1;
		System.out.println("Getting student with id: " + studentId);
		Student myStudent = studentDAO.findById(studentId);

		// change first name to "Scooby"
		System.out.println("Updating student ...");
		myStudent.setFirstName("Scooby");

		// update the student
		studentDAO.update(myStudent);

		// display the updated student
		System.out.println("Updated student: " + myStudent);
	}

	private void queryForStudentByLastName(StudentDAO studentDAO) {

		// get a list of students
		List<Student> theStudent = studentDAO.findByLastName("Doe");

		//display list of students
		for (Student tempStudent: theStudent) {
			System.out.println(tempStudent);
		}
	}

	private void queryForStudent(StudentDAO studentDAO) {

		// get a list of students
		List<Student> theStudent = studentDAO.findAll();

		//display list of students
		for (Student tempStudent: theStudent) {
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {

		Student tempStudent = new Student("Daffy","Duck","Daffy@luv2code.com");
		studentDAO.save(tempStudent);

		Student myStudent = studentDAO.findById(tempStudent.getId());
		System.out.println("Found the student " + myStudent);
	}

	private void createMultipleStudent(StudentDAO studentDAO) {

		// create multiple student
		System.out.println("Creating 3 student objects ...");
		Student tempStudent1 = new Student("John","Doe","John@luv2code.com");
		Student tempStudent2 = new Student("Mary","Public","Mary@luv2code.com");
		Student tempStudent3 = new Student("Bonita","Applebum","Bonita@luv2code.com");

		// save the student objects
		System.out.println("Saving the students ...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

	private void createStudent(StudentDAO studentDAO) {

		// create the student object
		System.out.println("Creating new student object ...");
		Student tempStudent = new Student("Paul","Doe","paul@luv2cod.com");

		// save the student object
		System.out.println("Saving the student ...");
		studentDAO.save(tempStudent);

		// display id of the saved student
		System.out.println("Saved student. Generated id: " + tempStudent.getId());
	}
}
