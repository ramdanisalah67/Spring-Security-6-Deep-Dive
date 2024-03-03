package com.example.springsecurityv6.Filter;


import com.example.springsecurityv6.Jwt.CustomAuthenticationProvider;
import com.example.springsecurityv6.Jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class MyCustomFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;
    private  AuthenticationManager authenticationManager;



    public MyCustomFilter() {
        // Instantiate AuthenticationManager with a list of AuthenticationProviders
        this.authenticationManager = new ProviderManager(Collections.singletonList(new CustomAuthenticationProvider()));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            String header = request.getHeader("x-profil");
     /*   if (header !=null && !header.equals("java")) {
            //System.out.println("you are Not a Java Developer");
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.getWriter().write("you are Not a Java Developer");
            return ;
        }
        if (header==null){
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.getWriter().write("you are Not a Java Developer");
            return;
        }
        System.out.println("--welcome----Java Developer");*/

        /*my Custom Authentication*/

        if ("java".equals(header)) {
            String token = jwtService.generateToken("user1");
            System.out.println("he Java Developer !!"+token);
            // Create an Authentication object
            Authentication auth = new UsernamePasswordAuthenticationToken("user2", "222");

            // Authenticate the user

            Authentication authenticated = authenticationManager.authenticate(auth);

            // Set the Authentication object in the SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authenticated);
        }

        filterChain.doFilter(request,response);
    }
}
