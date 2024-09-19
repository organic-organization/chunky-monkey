package com.lms.eureka.delivery.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum DeliveryExceptionCase {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 사용자가 존재하지 않습니다."),
    DELIVERY_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 배송이 존재하지 않습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;

}
