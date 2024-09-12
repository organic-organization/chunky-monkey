package com.lms.eureka.hub.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum HubExceptionCase {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 사용자가 존재하지 않습니다."),
    HUB_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 허브가 존재하지 않습니다."),
    DUPLICATE_NAME(HttpStatus.CONFLICT, "중복되는 이름입니다."),
    DUPLICATE_ADDRESS(HttpStatus.CONFLICT, "중복되는 주소입니다."),
    DUPLICATE_ROUTE_INDEX(HttpStatus.CONFLICT, "중복되는 경로 순서입니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
