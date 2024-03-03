package com.example.springsecurityv6.Controllers;

import com.example.springsecurityv6.Models.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/home/")
public class HomeController {

    @GetMapping("welcome")
    public  Message welcome(){
        return new Message("welcome salah-eddine");
    }

    @GetMapping("products")
    public  Message index(){
        return new Message("this is products page must be secured");
    }
}
