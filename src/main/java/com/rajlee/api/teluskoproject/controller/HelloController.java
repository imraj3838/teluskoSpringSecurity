package com.rajlee.api.teluskoproject.controller;

//import org.springframework.security.core.userdetails.User;
import com.rajlee.api.teluskoproject.models.User;
import com.rajlee.api.teluskoproject.service.UserService;
import com.sun.net.httpserver.HttpServer;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private UserService userService;

    @GetMapping("/csrftoken")
    public CsrfToken csrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping("/hello")
    public String Greet(HttpServletRequest request){
        return "Hello user";
    }

    @GetMapping("/getAll")
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }

    @PostMapping("/save")
    public void registerUser(@RequestBody User user){
        userService.registerUser(user);
    }
}
