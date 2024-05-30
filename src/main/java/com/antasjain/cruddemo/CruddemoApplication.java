package com.antasjain.cruddemo;

import com.antasjain.cruddemo.dao.StudentDAO;
import com.antasjain.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
			createStudent(studentDAO);
		};
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
