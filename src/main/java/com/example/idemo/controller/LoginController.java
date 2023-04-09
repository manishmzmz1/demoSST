package com.example.idemo.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {


    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            System.out.println(auth.getAuthorities());
            return "redirect:/user";
        }
        System.out.println("hey");
        return "login";
    }

    @RequestMapping(value = {"/login_failure_handler"}, method = RequestMethod.POST)
    public String loginFailureHandler() {
        System.out.println("Login failure handler....");

        return "login";
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    public String logout() {
        return "logout";
    }

    @RequestMapping(value = "403",method = RequestMethod.GET)
    public String accessDenie(){
        return "alerts/accessDenied";

    }

}
