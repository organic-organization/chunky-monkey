package com.lms.eureka.user.presentation.request;

public record UpdatePasswordRequestDto(
        String code,
        String existingPassword,
        String newPassword
) {
}
