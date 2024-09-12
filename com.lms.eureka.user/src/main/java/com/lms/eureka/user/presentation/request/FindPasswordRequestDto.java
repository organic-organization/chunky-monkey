package com.lms.eureka.user.presentation.request;

import com.lms.eureka.user.application.dto.UserDto;

public record FindPasswordRequestDto(
        String username
) {
    public UserDto toDto(){
        return UserDto.findPasswordOf(username);
    }
}
