package com.lms.eureka.delivery.domain.exception;

import lombok.Getter;

@Getter
public class DeliveryException extends RuntimeException{

    private final DeliveryExceptionCase exceptionCase;

    public DeliveryException(DeliveryExceptionCase exceptionCase) {
        super(exceptionCase.getMessage());
        this.exceptionCase = exceptionCase;
    }

}
