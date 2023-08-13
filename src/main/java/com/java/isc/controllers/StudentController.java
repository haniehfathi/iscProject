package com.java.isc.controllers;

import com.java.isc.models.Course;
import com.java.isc.models.Student;
import com.java.isc.repositories.CourseRepository;
import com.java.isc.services.CourseService;
import com.java.isc.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/student")
public class StudentController {

    StudentService studentService;
    CourseService courseService;
    CourseRepository courseRepository;

    @Autowired
    public StudentController(StudentService studentService, CourseService courseService, CourseRepository courseRepository) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.courseRepository = courseRepository;
    }

    @RequestMapping(value = "/courseAssignment",method = RequestMethod.GET)
    public  String addCourse(Model model){
        List<Course> courses= (List<Course>) courseRepository.findAll();
        model.addAttribute("courses",courses);
        model.addAttribute("student",new Student());
        model.addAttribute("studentsCources",courseService.findByStudentId());

        return "/pages/courseAssignment";
    }


    @PostMapping("/courseAssignment")
    public  String courseAssignment(@ModelAttribute(value = "courses") Course course, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return "pages/courseAssignment";

        studentService.courseAssignment(course);
        return  "redirect:/student/courseAssignment";    }
}
