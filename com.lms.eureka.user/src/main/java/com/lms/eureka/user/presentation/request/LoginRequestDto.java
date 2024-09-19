package com.lms.eureka.user.presentation.request;

import com.lms.eureka.user.application.dto.UserDto;

public record LoginRequestDto(
        String username,
        String password
) {
    public UserDto toDto(){
        return UserDto.loginOf(username, password);
    }
}
