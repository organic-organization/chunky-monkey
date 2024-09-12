package com.lms.eureka.company.presentation.controller;

import com.lms.eureka.company.presentation.response.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping("/api/user/{username}")
    CommonResponse getUserByUsername(@PathVariable("username") String username);

    @GetMapping("/api/user/{userId}")
    CommonResponse getUserByUserId(@PathVariable("userId") Long userId);
}