package com.example.idemo.controller;

import com.example.idemo.model.Faculty;
import com.example.idemo.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FacultyController {

    @Autowired
    FacultyService facultyService;



    @RequestMapping(value = "/admin/faculty",method = RequestMethod.GET)
    public String ListFaculty(Model model){
        model.addAttribute("faculties",facultyService.findAll());
        return "faculty/list";
    }

    @RequestMapping(value = "/admin/faculty/create",method = RequestMethod.GET)
    public String createFaculty(Model model){
        model.addAttribute("faculty",new Faculty());
        model.addAttribute("faculties",facultyService.findAll());
        return "faculty/create";
    }

    @RequestMapping(value = "/admin/faculty/create",method = RequestMethod.POST)
    public String saveFaculty(Model model, Faculty faculty){
        System.out.println(faculty);
        facultyService.saveData(faculty);
        model.addAttribute("success", "Created successfully");
        model.addAttribute("faculties",facultyService.findAll());
        model.addAttribute("faculty",new Faculty());
        return "faculty/create";
    }

    @RequestMapping(value = "/admin/faculty/update/{id}",method = RequestMethod.GET)
    public String updateFaculty(@PathVariable("id") Long id, Model model){
        model.addAttribute("faculty",facultyService.findOne(id));
        return "faculty/update";
    }

    @RequestMapping(value = "/admin/faculty/update/{id}",method = RequestMethod.POST)
    public String saveUpdateFaculty(Model model, Faculty faculty){
        facultyService.saveData(faculty);
        model.addAttribute("success", "Updated successfully");
        model.addAttribute("faculties",facultyService.findAll());
        return "faculty/list";
    }
}
