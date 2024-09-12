package com.lms.eureka.slack.domain.exception;

import lombok.Getter;

@Getter
public class SlackException extends RuntimeException{

    private final SlackExceptionCase exceptionCase;

    public SlackException(SlackExceptionCase exceptionCase) {
        super(exceptionCase.getMessage());
        this.exceptionCase = exceptionCase;
    }

}
