package com.example.idemo.controller;

import com.example.idemo.model.User;
import com.example.idemo.repository.RoleRepository;
import com.example.idemo.repository.UserRepository;
import com.example.idemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sun.invoke.empty.Empty;

@Controller
public class RegistrationController {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleRepository.findAll());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String saveUser(Model model, User user, BindingResult bindingResult) {
        User userExist = userService.findUserByUsername(user.getUsername());
        System.out.println(user.getRoles());
        if (userExist != null) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "User already exist!!");
            model.addAttribute("user", new User());
            model.addAttribute("roles", roleRepository.findAll());
            model.addAttribute("error", "User Already exist");
            return "registration";
        } else if(user.getRoles().isEmpty()) {
            System.out.println("empty role executed");
            bindingResult
                    .rejectValue("roles", "error.user",
                            "Invalid Role!!");
            model.addAttribute("user", new User());
            model.addAttribute("roles", roleRepository.findAll());
            model.addAttribute("error", "Invalid Role!!");
            return "registration";
        }
        else{
            System.out.println(user);
            userService.saveData(user);
            System.out.println("user SAVED");
            model.addAttribute("roles", roleRepository.findAll());
            model.addAttribute("user", new User());
            model.addAttribute("success", "Created successfully");
            return "registration";
        }


    }

}
