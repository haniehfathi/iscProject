package com.java.isc.services;

import com.java.isc.models.Course;
import com.java.isc.models.Student;
import com.java.isc.repositories.CourseRepository;
import com.java.isc.repositories.ProfessorRepository;
import com.java.isc.repositories.StudentRepository;
import com.java.isc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    CourseRepository courseRepository;
    StudentRepository studentRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public Iterable<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public Course addCourse(Course course) throws Exception {
        Course courseIfExist=courseRepository.findByTitle(course.getTitle());
        if(courseIfExist !=null )
            throw new Exception("course Existed");
    
        return courseRepository.save(course);
    }
    public Course findOne(Long id) throws Exception {
        return Optional.ofNullable(courseRepository.findOne(id))
                .orElseThrow(()->new Exception("Course not found"));
    }

    public  List<Course> findByStudentId(){
        try {
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            Student std=studentRepository.findByUsernameQuery(authentication.getName());
            return courseRepository.findCoursesByStudentId(std);
        }
        catch(Exception e)
        {
            System.out.printf("exception in findByStudentId  with exception type: "+e.toString());
            return null;
        }
    }

}
