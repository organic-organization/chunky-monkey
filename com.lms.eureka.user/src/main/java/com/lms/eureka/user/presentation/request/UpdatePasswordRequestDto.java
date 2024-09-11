package com.lms.eureka.user.presentation.request;

import com.lms.eureka.user.application.dto.UserDto;

public record UpdatePasswordRequestDto(
        String code,
        String username,
        String password
) {
    public UserDto toDto(){
        return UserDto.updatePasswordOf(username, password);
    }
}
