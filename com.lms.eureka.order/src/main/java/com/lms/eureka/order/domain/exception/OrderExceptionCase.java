package com.lms.eureka.order.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum OrderExceptionCase {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자가 존재하지 않습니다."),
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 주문이 존재하지 않습니다."),
    ORDER_FAIL(HttpStatus.BAD_REQUEST, "주문에 실패하였습니다.")
    ;
    private final HttpStatus httpStatus;
    private final String message;

}
