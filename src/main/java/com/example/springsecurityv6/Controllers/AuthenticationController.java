package com.example.springsecurityv6.Controllers;


import com.example.springsecurityv6.Models.AuthenticationRequest;
import com.example.springsecurityv6.Models.Message;
import com.example.springsecurityv6.Models.RegisterRequest;
import com.example.springsecurityv6.Services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("auth")
    public Message login(@RequestBody AuthenticationRequest auth){

        return authenticationService.Authenticate(auth);

    }

    @PostMapping("register")
    public Message register(@RequestBody RegisterRequest auth){

        return authenticationService.register(auth);

    }


}
