package com.lms.eureka.hub.domain.exception;

import lombok.Getter;

@Getter
public class HubException extends RuntimeException{

    private final HubExceptionCase exceptionCase;

    public HubException(HubExceptionCase exceptionCase) {
        super(exceptionCase.getMessage());
        this.exceptionCase = exceptionCase;
    }

}
