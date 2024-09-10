package com.lms.eureka.user.presentation.request;

import com.lms.eureka.user.application.dto.UserDto;

public record UserUpdateRequestDto(
        String role,
        String slackId
) {
    public UserDto toDto() {
        return UserDto.updateOf(role, slackId);
    }
}
