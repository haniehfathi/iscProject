package com.java.isc;

import com.java.isc.enums.Roles;
import com.java.isc.models.Course;
import com.java.isc.models.Professor;
import com.java.isc.models.Student;
import com.java.isc.models.User;
import com.java.isc.repositories.CourseRepository;
import com.java.isc.repositories.ProfessorRepository;
import com.java.isc.repositories.StudentRepository;
import com.java.isc.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;

@SpringBootApplication
public class IscApplication {

	public static void main(String[] args) {
		SpringApplication.run(IscApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(PasswordEncoder encoder , StudentRepository studentRepository, CourseRepository courseRepository, ProfessorRepository professorRepository, UserRepository userRepository){
		return args -> {

			studentRepository.save(new Student("roham", "mahmoudpour", "roham@gmail.com", "0123456", "tehran","user1","123456"));
			userRepository.save(new User("user1", encoder.encode("123456"), true, "STUDENT",studentRepository.findByUsernameQuery("user1")));
			userRepository.save(new User("user2", encoder.encode("123456"), true, "ADMIN"));
			professorRepository.save(new Professor("hanieh", "fathi", "hnieh@gmail.com", "0123456", "tehran"));
			courseRepository.save(new Course("math", (short) 3,professorRepository.getByEmail("hnieh@gmail.com")));
			courseRepository.save(new Course("physics", (short) 3,professorRepository.getByEmail("hnieh@gmail.com")));

		};
	}
}
