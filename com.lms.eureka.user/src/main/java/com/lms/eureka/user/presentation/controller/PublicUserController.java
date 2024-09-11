package com.lms.eureka.user.presentation.controller;

import com.lms.eureka.user.application.service.UserService;
import com.lms.eureka.user.presentation.request.FindPasswordRequestDto;
import com.lms.eureka.user.presentation.request.LoginRequestDto;
import com.lms.eureka.user.presentation.request.SignUpRequestDto;
import com.lms.eureka.user.presentation.request.UpdatePasswordRequestDto;
import com.lms.eureka.user.presentation.response.CommonResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/public/user")
public class PublicUserController {

    private final UserService userService;

    @PostMapping("/signUp")
    public CommonResponse<Void> signUp(
            //@Valid
            @RequestBody
            SignUpRequestDto request
    ){
        userService.signUp(request.toDto());
        return CommonResponse.success("회원 가입 성공");
    }

    @PostMapping("/login")
    public CommonResponse<Void> login(
            HttpServletResponse response,
            @Valid
            @RequestBody
            LoginRequestDto dto
    ){
        userService.login(response, dto);
        return CommonResponse.success("로그인 성공");
    }

    @PostMapping("/find/password")
    public CommonResponse<Void> findPassword(@RequestBody FindPasswordRequestDto dto) {
        userService.findPassword(dto);
        return CommonResponse.success("코드 보내기 성공");
    }

    @PostMapping("/update/password")
    public CommonResponse<Void> updatePassword(@RequestBody UpdatePasswordRequestDto dto) {
        userService.updatePassword(dto.code(), dto.toDto());
        return CommonResponse.success("비밀번호 변경 성공");
    }
}
