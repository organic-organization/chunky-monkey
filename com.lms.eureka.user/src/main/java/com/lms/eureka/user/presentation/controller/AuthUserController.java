package com.lms.eureka.user.presentation.controller;

import com.lms.eureka.user.application.service.UserService;
import com.lms.eureka.user.infra.jwt.UserDetailsImpl;
import com.lms.eureka.user.presentation.request.UserUpdateRequestDto;
import com.lms.eureka.user.presentation.response.UserResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "User API", description = "User 관련 API 입니다.")
public class AuthUserController {

    private final UserService userService;

    @GetMapping("/company-manager/user")
    public UserResponseDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return UserResponseDto.from(userService.getUserInfoByUserName(userDetails.getUsername()));
    }

    @GetMapping("/master/user")
    public Page<UserResponseDto> getUserInfo(
            @ParameterObject @PageableDefault(
                    size = 10, sort = {"username"}, direction = Sort.Direction.DESC
            ) Pageable pageable
    ){
        return userService.getUserInfos(pageable).map(UserResponseDto::from);
    }

    @GetMapping("/master/user/{userId}")
    public UserResponseDto getUserInfoByUserId(@PathVariable("userId") Long userId){
        return UserResponseDto.from(userService.getUserInfoByUserId(userId));
    }

    @PutMapping("/master/user/{userId}")
    public UserResponseDto updateUserInfo(@PathVariable("userId") Long userId, @RequestBody UserUpdateRequestDto request){
        return UserResponseDto.from(userService.updateUserInfo(userId, request.toDto()));
    }

    @DeleteMapping("/master/user/{userId}")
    public String deleteUserInfo(@PathVariable("userId") Long userId){
        userService.deleteUserInfo(userId);
        return "User deleted";
    }
}
