package com.example.idemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {


    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String dash(){
        return "home";
    }

    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    public String admin(){
        return "normalUser/userDash";
    }

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String user(){
        return "normalUser/userDash";
    }
}
