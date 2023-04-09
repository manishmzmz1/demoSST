package com.example.idemo.controller;

import com.example.idemo.model.Faculty;
import com.example.idemo.model.Student;
import com.example.idemo.model.User;
import com.example.idemo.service.FacultyService;
import com.example.idemo.service.StudentService;
import com.example.idemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    FacultyService facultyService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/student",method = RequestMethod.GET)
    public String ListStudent(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        model.addAttribute("students",studentService.findAllByCreatedUser(user));
        return "student/list";
    }

//    view list for SUPER ADMIN
    @RequestMapping(value = "/admin/student",method = RequestMethod.GET)
    public String ListAllStudent(Model model){
        model.addAttribute("students",studentService.findAll());
        return "admin/studentlist";
    }

    @RequestMapping(value = "/user/student/create",method = RequestMethod.GET)
    public String createStudent(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
//        System.out.println(user);
        List<Student> students = studentService.findAllByCreatedUser(user);
//        System.out.println(students);
        model.addAttribute("student",new Student());
        model.addAttribute("students",students);
        model.addAttribute("faculties",facultyService.findAll());
        return "student/create";
    }

    @RequestMapping(value = "/user/student/create",method = RequestMethod.POST)
    public String saveStudent(Model model, Student student){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUsername(auth.getName());
        student.setCreatedUser(user);
        studentService.saveStudent(student);
        model.addAttribute("success", "Created successfully");
        model.addAttribute("students",studentService.findAllByCreatedUser(user));
        model.addAttribute("faculties",facultyService.findAll());
        model.addAttribute("student",new Student());
        return "student/create";
    }

    @RequestMapping(value = "/user/student/update/{id}",method = RequestMethod.GET)
    public String updateStudent(@PathVariable("id") Long id, Model model){
        model.addAttribute("student",studentService.findOne(id));
        model.addAttribute("faculties",facultyService.findAll());
        return "student/update";
    }

    @RequestMapping(value = "/user/student/update/{id}",method = RequestMethod.POST)
    public String saveUpdateStudent(Model model, Student student){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        User user = userService.findUserByUsername(auth.getName());
        student.setCreatedUser(user);
        studentService.saveStudent(student);
        model.addAttribute("success", "Updated successfully");
        model.addAttribute("students",studentService.findAllByCreatedUser(user));
        return "student/list";
    }
}
