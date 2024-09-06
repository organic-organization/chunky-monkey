package com.lms.eureka.user.presentation.request;

import com.lms.eureka.user.application.dto.UserDto;

public record LoginRequestDto(
        String userName,
        String password
) {
    public UserDto toDto(){
        return UserDto.builder()
                .userName(userName)
                .password(password)
                .build();
    }
}
