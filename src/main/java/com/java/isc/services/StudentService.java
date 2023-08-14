package com.java.isc.services;

import com.java.isc.models.Course;
import com.java.isc.models.Student;
import com.java.isc.repositories.CourseRepository;
import com.java.isc.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.lang.model.type.ErrorType;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    StudentRepository studentRepository;
    CourseService courseService;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseService courseService) {
        this.studentRepository = studentRepository;
        this.courseService = courseService;
    }

    public Iterable<Student> getAllStudent(){
        return studentRepository.findAll();
    }


    public Student findOne(Long id) throws Exception {
//        final Optional<Student> student=Optional.ofNullable(studentRepository.findOne(id));
//        return student.orElseThrow();
        return Optional.ofNullable(studentRepository.findOne(id))
                .orElseThrow(()->new Exception("Student not found"));
    }

    public Student findByEmail(String s) throws Exception {
        final Optional<Student> student=Optional.ofNullable(studentRepository.findByEmail(s));
        return student.orElseThrow(()->new Exception("Student not found"));
    }

    public Student addStudent(Student std) throws Exception {
       Student studentIfExist=studentRepository.findByEmail(std.getEmail());

        if(studentIfExist !=null )
            throw new Exception("student Existed");
         return studentRepository.save(std);
    }

    public void courseAssignment(Course course){
        short studentunits=0;
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        boolean isAuth=authentication.getName()=="anonymousUser"?false:true;
        String role= authentication.getAuthorities().stream().map(auth->auth.getAuthority()).collect(Collectors.joining());

        if(isAuth && role.equals("STUDENT")) {
            Student std=studentRepository.findByUsernameQuery(authentication.getName());

            studentunits=((short) (courseService.findByStudentId().stream().mapToInt(s->s.getUnit()).sum()+course.getUnit()));
            if(studentunits<24)
            {
                std.setNumberOfUnits(studentunits);
                studentRepository.courseAssignment(std,course);
            }

        }
    }

}
