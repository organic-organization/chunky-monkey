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
    public CommonResponse<Void> checkUserByUsername(@PathVariable("username") String username) {

        if(!userService.checkUserByUsername(username)){
            return CommonResponse.error(UserExceptionCase.USER_NOT_FOUND);
        }

        return CommonResponse.success(null);
    }

    @GetMapping("/userId/{userId}/")
    public CommonResponse<Void> checkUserByUserId(@PathVariable("userId") Long userId) {

        if(userService.checkUserByUserId(userId)) {
            return CommonResponse.error(UserExceptionCase.USER_NOT_FOUND);
        }
        else {
            return CommonResponse.success(null);
        }
    }
}
