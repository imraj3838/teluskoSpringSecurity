package com.rajlee.api.teluskoproject.filters;


import com.rajlee.api.teluskoproject.models.UserDetailsImpl;
import com.rajlee.api.teluskoproject.service.JwtService;
import com.rajlee.api.teluskoproject.service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    JwtService jwtService;

    @Autowired
    ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, IOException {

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String userName = null;

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
            userName = jwtService.extractUserName(token);
            System.out.println("Extracted username from token: " + userName); // Debugging statement
        }

        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){

            UserDetailsImpl userDetails = (UserDetailsImpl) context.getBean(MyUserDetailsService.class).loadUserByUsername(userName);
            System.out.println("UserDetails: " + userDetails.getUserEmail());
            if(userDetails != null && jwtService.validateToken(token, userDetails)){
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                System.out.println("User authenticated successfully"); // Debugging statement
            }
        }
        filterChain.doFilter(request, response);
    }
}
