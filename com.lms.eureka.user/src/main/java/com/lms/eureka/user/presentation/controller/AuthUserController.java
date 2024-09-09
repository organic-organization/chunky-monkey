package com.lms.eureka.user.presentation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthUserController {
    @GetMapping("/master/user")
    public String getUsername(){
        return "test";
    }
}
