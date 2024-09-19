package com.lms.eureka.order.domain.exception;

import lombok.Getter;

@Getter
public class OrderException extends RuntimeException{

    private final OrderExceptionCase exceptionCase;

    public OrderException(OrderExceptionCase exceptionCase) {
        super(exceptionCase.getMessage());
        this.exceptionCase = exceptionCase;
    }

}
