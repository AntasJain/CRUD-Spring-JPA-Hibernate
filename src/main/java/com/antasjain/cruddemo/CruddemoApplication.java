package com.antasjain.cruddemo;

import com.antasjain.cruddemo.dao.StudentDAO;
import com.antasjain.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class CruddemoApplication {
	Scanner scanner;
	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
			boolean keepAtIt = true;
			while(keepAtIt){
				System.out.println("Enter 1 to Create Student");
				System.out.println("Enter 2 to Get Student Info");
				System.out.println("Enter 3 to Update Student Details");
				System.out.println("Enter 4 to Delete Student Details");
				System.out.println("Enter 5 to Delete All Students");
				System.out.println("Enter 0 to Exit");
				scanner = new Scanner(System.in);
				int choice = scanner.nextInt();
				switch(choice){
					case 0: keepAtIt=false;break;
					case 1:createStudent(studentDAO);break;
					case 2:readStudent(studentDAO);break;
					case 3: updateStudent(studentDAO);break;
					case 4: deleteStudent(studentDAO); break;
					case 5:deleteAllStudents(studentDAO);break;
					default:
						System.out.println("Wrong Entry");

				}


			}

			//createMulitpleStudents(studentDAO);

			//queryForStudents(studentDAO);
			//queryForStudentsByLastName(studentDAO);

			//deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		System.out.println("Deleting all Students from Table");
		int rows=studentDAO.deleteAll();
		System.out.println("Number of rows deleted:"+rows);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		System.out.println("Enter Student Id to Delete: ");
		int studentId = scanner.nextInt();
		System.out.println("Deleting student with id: "+studentId);
		studentDAO.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {
		System.out.println("Enter Student ID to update Info:");
		int studentId = scanner.nextInt();

		System.out.println("getting student with id: "+studentId);

		Student oldStudent = studentDAO.findById(studentId);
		System.out.println("Press: \n1 to update FirstName\n2 to update LastName\n3 to update Email");
		int choice = scanner.nextInt();
		System.out.println("Enter value to Update: ");

		String updateVal = scanner.next();
		System.out.println("Student details before updating:\n"+oldStudent);
		switch (choice){
			case 3: oldStudent.setEmail(updateVal); break;
			case 2: oldStudent.setLastName(updateVal); break;
			case 1: oldStudent.setFirstName(updateVal); break;
		}

		studentDAO.update(oldStudent);
		//readStudent(studentDAO);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findByLastName("Jain");
		for(Student student:students){
			System.out.println(student);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		//get list of students
		//display list of students

		List<Student> students = studentDAO.findAll();

		for(Student student:students){
			System.out.println(student);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		System.out.println("Enter Student ID to get Info:");
		int id = scanner.nextInt();
		//retrieve student based on id
		Student student=studentDAO.findById(id);
		//display student
		System.out.println(student);
	}

	private void createMulitpleStudents(StudentDAO studentDAO) {
		Student tempStudent1 = new Student("Martian","Manhunter","mm@dc.com");
		Student tempStudent2 = new Student("Diana","Prince","dp@dc.com");
		Student tempStudent3 = new Student("Barry","Allen","ba@dc.com");
		//save student obj
		System.out.println("Saving the Students...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
		//display id of saved student
		System.out.println("Saved Student. Generated id: "+tempStudent1.getId());
		System.out.println("Saved Student. Generated id: "+tempStudent2.getId());
		System.out.println("Saved Student. Generated id: "+tempStudent3.getId());
	}

	private void createStudent(StudentDAO studentDAO) {
		System.out.println("Enter Student Details as Prompted!");

		System.out.println("--> First Name: ");

		String firstName = scanner.next();
		System.out.println("--> Last Name: ");
		String lastName = scanner.next();
		System.out.println("--> EmailId: ");
		String email = scanner.next();
		//create student obj
		Student tempStudent = new Student(firstName,lastName,email);
		//save student obj
		System.out.println("Saving the Student to Database...");
		studentDAO.save(tempStudent);
		//display id of saved student
		System.out.println("Saved Student @ ID: " + tempStudent.getId());
	}

}
