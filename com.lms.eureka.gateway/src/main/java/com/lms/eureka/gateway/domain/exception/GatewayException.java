package com.lms.eureka.gateway.domain.exception;

import lombok.Getter;

@Getter
public class GatewayException extends RuntimeException{

    private final GatewayExceptionCase exceptionCase;

    public GatewayException(GatewayExceptionCase exceptionCase) {
        super(exceptionCase.getMessage());
        this.exceptionCase = exceptionCase;
    }

}
