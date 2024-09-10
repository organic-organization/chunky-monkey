package com.lms.eureka.user.presentation.response;

import com.lms.eureka.user.application.dto.UserDto;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserResponseDto(
        Long userId,
        String username,
        String slackId,
        String role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static UserResponseDto from(UserDto dto) {
        return UserResponseDto.builder()
                .userId(dto.userId())
                .username(dto.username())
                .slackId(dto.slackId())
                .role(dto.role().getAuthority())
                .createdAt(dto.createdAt())
                .updatedAt(dto.updatedAt() == null ? null : dto.updatedAt())
                .build();
    }
}
