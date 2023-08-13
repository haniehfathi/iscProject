package com.java.isc.controllers;

import com.java.isc.config.CustomUserDetails;
import com.java.isc.models.Course;
import com.java.isc.models.Professor;
import com.java.isc.models.Student;
import com.java.isc.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/")
public class MainController {

    ProfessorRepository professorRepository;

    @Autowired
    public MainController(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(Model model){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        boolean isAuth=authentication.getName()=="anonymousUser"?false:true;
        String adminRole= authentication.getAuthorities().stream().map(aaa->aaa.getAuthority()).collect(Collectors.joining());

        model.addAttribute("isAuth",isAuth);
        if(isAuth) {
            model.addAttribute("name", authentication.getName());
            model.addAttribute("role", adminRole);
        }
        return "/pages/index";
    }

    @RequestMapping(value = "/errorPage",method = RequestMethod.GET)
    public String error(){
        return "/pages/errorPage";
    }

}
