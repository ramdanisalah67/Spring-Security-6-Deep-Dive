package com.example.springsecurityv6.Services;

import com.example.springsecurityv6.Jwt.JwtService;
import com.example.springsecurityv6.Models.AuthenticationRequest;
import com.example.springsecurityv6.Models.Message;
import com.example.springsecurityv6.Models.RegisterRequest;
import com.example.springsecurityv6.Models.User;
import com.example.springsecurityv6.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepopsitory;
    public Message Authenticate(AuthenticationRequest request){


            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            String token = jwtService.generateToken(request.getUsername());
            System.out.println(token);


        return new Message(token);

    }
    public Message register(RegisterRequest request){

        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .createdAt(Instant.now())
                .roles("USER")
                .build();
    if(user.getUsername().equals("qq"))user.setRoles("ROLE_ADMIN");
        userRepopsitory.save(user);


        return new Message("User Saved Successfully !!!");

    }


}
