package com.example.springsecurityv6.Filter;


import com.example.springsecurityv6.Jwt.JwtService;
import com.example.springsecurityv6.Models.User;
import com.example.springsecurityv6.Models.UserDetailsServiceImpl;
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
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class MyCustomFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");


         if(header ==null || !header.startsWith("Bearer ")){
             filterChain.doFilter(request,response);
             return;
         }
         String token =header.substring(7);
         String username = jwtService.extractUsername(token);
         if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
             User user = (User) userDetailsService.loadUserByUsername(username);

             if(jwtService.validateToken(token,user)){
                 UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                         user,null,user.getAuthorities()
                 );
                 auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                 SecurityContextHolder.getContext().setAuthentication(auth);
             }
         }


        filterChain.doFilter(request,response);
    }
}


















