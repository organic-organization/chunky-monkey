package com.lms.eureka.user.application.dto;

import com.lms.eureka.user.domain.model.User;
import com.lms.eureka.user.domain.model.UserRoleEnum;
import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder(access = AccessLevel.PRIVATE)
public record UserDto(
        Long userId,
        String username,
        String password,
        UserRoleEnum role,
        String slackId,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime updatedAt,
        String updatedBy,
        LocalDateTime deletedAt,
        String deletedBy
) {

    public static UserDto loginOf(String username, String password) {
        return UserDto.builder()
                .username(username)
                .password(password)
                .build();
    }

    public static UserDto signUpOf(String username, String password, String role, String slackId) {
        return UserDto.builder()
                .username(username)
                .password(password)
                .role(UserRoleEnum.fromString(role))
                .slackId(slackId)
                .build();
    }

    public static UserDto updateOf(String role, String slackId){
        return UserDto.builder()
                .role(UserRoleEnum.valueOf(role))
                .slackId(slackId)
                .build();
    }

    public User toEntity(String encodePassword) {
        return User.create(username, encodePassword, role, slackId);
    }

    public static UserDto from(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .slackId(user.getSlackId())
                .createdAt(user.getCreatedAt())
                .createdBy(user.getCreatedBy())
                .updatedAt(user.getUpdatedAt())
                .updatedBy(user.getUpdatedBy())
                .deletedAt(user.getDeletedAt())
                .deletedBy(user.getDeletedBy())
                .build();
    }
}
