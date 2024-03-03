package com.example.springsecurityv6.Controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerLogin {


    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
