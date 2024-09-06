package com.lms.eureka.user.application.dto;

import com.lms.eureka.user.domain.model.User;
import com.lms.eureka.user.domain.model.UserRoleEnum;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserDto(
        Long userId,
        String userName,
        String password,
        UserRoleEnum role,
        boolean isDeleted,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime updatedAt,
        String updatedBy,
        LocalDateTime deletedAt,
        String deletedBy
) {
    public User toEntity(String encodePassword) {
        return User.builder()
                .userName(userName)
                .password(encodePassword)
                .role(role)
                .build();
    }
}
