package com.antasjain.cruddemo;

import com.antasjain.cruddemo.dao.StudentDAO;
import com.antasjain.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLOutput;
import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
			//createStudent(studentDAO);
			//createMulitpleStudents(studentDAO);
			//readStudent(studentDAO);
			//queryForStudents(studentDAO);
			//queryForStudentsByLastName(studentDAO);
			//updateStudent(studentDAO);
			//deleteStudent(studentDAO);
			deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		System.out.println("Deleting all Students from Table");
		int rows=studentDAO.deleteAll();
		System.out.println("Number of rows deleted:"+rows);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 6;
		System.out.println("Deleting student with id: "+studentId);
		studentDAO.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {
		int studentId = 1;
		System.out.println("getting student with id: "+studentId);
		Student oldStudent = studentDAO.findById(studentId);
		oldStudent.setEmail("aj@dc.com");
		studentDAO.update(oldStudent);
		readStudent(studentDAO);
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
		//create a student obj

		//save student

		//display id of saved student
		int id = 1;
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
		//create student obj
		Student tempStudent = new Student("Antas","Jain","antasjain@github.com");
		//save student obj
		System.out.println("Saving the Student...");
		studentDAO.save(tempStudent);
		//display id of saved student
		System.out.println("Saved Student. Generated id: "+tempStudent.getId());
	}

}
