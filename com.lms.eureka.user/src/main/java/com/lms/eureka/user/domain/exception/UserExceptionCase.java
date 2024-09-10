package com.lms.eureka.user.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserExceptionCase {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "해당하는 유저가 존재하지 않습니다."),
    PASSWORD_NOT_MATCH(HttpStatus.UNAUTHORIZED.value(), "해당하는 유저가 존재하지 않습니다.")

    ;

    private final int httpStatus;
    private final String message;

}
