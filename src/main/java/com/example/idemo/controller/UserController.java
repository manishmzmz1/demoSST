package com.example.idemo.controller;

import com.example.idemo.model.User;
import com.example.idemo.repository.RoleRepository;
import com.example.idemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/admin/user",method = RequestMethod.GET)
    public String ListUser(Model model){
        System.out.println(userService.findAll());
        model.addAttribute("users",userService.findAll());
        return "username/list";
    }

    @RequestMapping(value = "/admin/user/create",method = RequestMethod.GET)
    public String createUser(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("roles",roleRepository.findAll());
        model.addAttribute("users",userService.findAll());
        return "username/create";
    }

    @RequestMapping(value = "/admin/user/create",method = RequestMethod.POST)
    public String saveUser(Model model, User user, BindingResult bindingResult){

        User userExist =userService.findUserByUsername(user.getUsername());
        if (userExist != null) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "User already exist!!");
            model.addAttribute("user",new User());
            model.addAttribute("roles",roleRepository.findAll());
            model.addAttribute("users",userService.findAll());
            model.addAttribute("error", "User Already exist");
            return "username/create";
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
            model.addAttribute("roles",roleRepository.findAll());
            model.addAttribute("user",new User());
            model.addAttribute("users",userService.findAll());
            model.addAttribute("success", "Created successfully");
            return "username/create";
        }
    }

    @RequestMapping(value = "/admin/user/update/{id}",method = RequestMethod.GET)
    public String updateFaculty(@PathVariable("id") Long id, Model model){
        model.addAttribute("user",userService.findOne(id));
        model.addAttribute("roles",roleRepository.findAll());
        return "username/update";
    }

    @RequestMapping(value = "/admin/user/update/{id}",method = RequestMethod.POST)
    public String saveUpdateFaculty(@PathVariable("id") Long id,Model model, User user, BindingResult bindingResult){

        User userExist =userService.findUserByUsername(user.getUsername());

        if (userExist != null) {

            if(!userExist.getId().equals(user.getId())){
                bindingResult
                        .rejectValue("username", "error.user",
                                "User already exist!!");
                model.addAttribute("user",userService.findOne(id));
                model.addAttribute("roles",roleRepository.findAll());
                model.addAttribute("users",userService.findAll());
                model.addAttribute("error", "Username Already exist");
                return "username/update";

            }else if(user.getRoles().isEmpty()) {
                System.out.println("empty role executed");
                bindingResult
                        .rejectValue("roles", "error.user",
                                "Invalid Role!!");
                model.addAttribute("user",userService.findOne(id));
                model.addAttribute("roles", roleRepository.findAll());
                model.addAttribute("error", "Invalid Role!!");
                return "username/update";
            }
            else{
                System.out.println(user);
                userService.saveData(user);
                System.out.println("user Updated");
                model.addAttribute("roles",roleRepository.findAll());
                model.addAttribute("users",userService.findAll());
                model.addAttribute("success", "Updated successfully");
                return "username/list";
            }


        }else if(user.getRoles().isEmpty()) {
            System.out.println("empty role executed");
            bindingResult
                    .rejectValue("roles", "error.user",
                            "Invalid Role!!");
            model.addAttribute("user",userService.findOne(id));
            model.addAttribute("roles", roleRepository.findAll());
            model.addAttribute("error", "Invalid Role!!");
            return "username/update";
        }
        else{
            System.out.println(user);
            userService.saveData(user);
            System.out.println("user updated");
            model.addAttribute("roles",roleRepository.findAll());
            model.addAttribute("users",userService.findAll());
            model.addAttribute("success", "Updated successfully");
            return "username/list";
        }
    }
}
