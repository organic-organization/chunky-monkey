package com.lms.eureka.user.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserExceptionCase {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 유저가 존재하지 않습니다."),
    PASSWORD_NOT_MATCH(HttpStatus.UNAUTHORIZED, "해당하는 유저가 존재하지 않습니다."),
    FIND_PASSWORD_FAIL(HttpStatus.BAD_REQUEST, "비밀번호 찾기 과정에서 에러가 발생했습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;

}
