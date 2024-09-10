package com.lms.eureka.user.domain.exception;

import lombok.Getter;

@Getter
public class UserException extends RuntimeException{

    private final UserExceptionCase exceptionCase;

    public UserException(UserExceptionCase exceptionCase) {
        super(exceptionCase.getMessage());
        this.exceptionCase = exceptionCase;
    }

}
