package com.antasjain.cruddemo;

import com.antasjain.cruddemo.dao.StudentDAO;
import com.antasjain.cruddemo.entity.Student;
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
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
			//createStudent(studentDAO);
			//createMulitpleStudents(studentDAO);
			//readStudent(studentDAO);
			queryForStudents(studentDAO);
		};
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
		int id = 3;
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
