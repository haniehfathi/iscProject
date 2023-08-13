package com.java.isc.controllers;

import com.java.isc.models.Course;
import com.java.isc.models.Professor;
import com.java.isc.models.Student;
import com.java.isc.models.User;
import com.java.isc.services.UserService;
import com.java.isc.services.ProfessorService;
import com.java.isc.services.CourseService;
import com.java.isc.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@RequestMapping("/admin")
@Controller
public class AdminController {

    ProfessorService professorService;
    StudentService studentService;
    CourseService courseService;
    UserService userService;

    @Autowired
    public AdminController(ProfessorService professorService, StudentService studentService, CourseService courseService, UserService userService) {
        this.professorService = professorService;
        this.studentService = studentService;
        this.courseService = courseService;
        this.userService = userService;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String admin(){

        return "/pages/admin";
    }

    @RequestMapping(value = "/addStudent",method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addStudent(Model model){
        model.addAttribute("student",new Student());
        model.addAttribute("students",studentService.getAllStudent());
        return "/pages/addStudent";
    }

    @RequestMapping(value = "/addCourse",method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addCourse(Model model){
        model.addAttribute("course",new Course());
        model.addAttribute("courses",courseService.getAllCourses());
        model.addAttribute("professors",professorService.getAllProfessor());

        return "/pages/addCourse";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/addProfessor",method = RequestMethod.GET)
    public   String addProfessor(Model model){
        model.addAttribute("professor",new Professor());
        model.addAttribute("professors",professorService.getAllProfessor());

        return "/pages/addProfessor";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addStudent")
    public  String addStudent(@ModelAttribute  @Valid Student student, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors())
            return "/pages/addStudent";

        studentService.addStudent(student);
        userService.addUser(new User(student.getUsername(), student.getNationalCode(), true, "STUDENT",student));

        return  "redirect:/admin/addStudent";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addCourse")
    public  String addCourse(@ModelAttribute @Valid Course course, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors())
            return "/pages/addCourse";

        courseService.addCourse(course);
        return  "redirect:/admin/addCourse";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addProfessor")
    public   String addProfessor(@ModelAttribute @Valid Professor professor, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors())
            return "/pages/addProfessor";

        professorService.addProfessor(professor);
        return  "redirect:/admin/addProfessor";
    }

}
