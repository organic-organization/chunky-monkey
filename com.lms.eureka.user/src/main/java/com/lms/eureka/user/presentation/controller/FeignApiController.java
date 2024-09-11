package com.lms.eureka.user.presentation.controller;

import com.lms.eureka.user.application.service.UserService;
import com.lms.eureka.user.domain.exception.UserException;
import com.lms.eureka.user.domain.exception.UserExceptionCase;
import com.lms.eureka.user.presentation.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class FeignApiController {

    private final UserService userService;

    @GetMapping("/{username}")
    public CommonResponse<Void> getUserByUsername(@PathVariable("username") String username) {
       boolean result = userService.getUserInfoByUserName(username) != null;

       if(!result) {
           throw new UserException(UserExceptionCase.USER_NOT_FOUND);
       }
       else {
           return CommonResponse.success(null);
       }
    }

    @GetMapping("/{userId}")
    public CommonResponse<Void> getUserByUserId(@PathVariable("userId") Long userId) {
        boolean result = userService.getUserInfoByUserId(userId) != null;

        if(!result) {
            throw new UserException(UserExceptionCase.USER_NOT_FOUND);
        }
        else {
            return CommonResponse.success(null);
        }
    }
}
