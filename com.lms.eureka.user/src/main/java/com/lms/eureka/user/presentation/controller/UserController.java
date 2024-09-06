package com.lms.eureka.user.presentation.controller;

import com.lms.eureka.user.presentation.request.LoginRequestDto;
import com.lms.eureka.user.presentation.request.SignUpRequestDto;
import com.lms.eureka.user.application.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/public/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<Boolean> signUp(
            @Valid
            @RequestBody
            SignUpRequestDto request
    ){
        userService.signUp(request.toDto());
        return ResponseEntity.ok(true);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(
            HttpServletResponse response,
            @Valid
            @RequestBody
            LoginRequestDto dto
    ){
        userService.login(response, dto);
        return ResponseEntity.ok("OK");
    }
}
