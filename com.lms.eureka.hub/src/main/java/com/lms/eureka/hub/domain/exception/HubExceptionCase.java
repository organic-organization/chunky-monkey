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
    DUPLICATE_ROUTE_INDEX(HttpStatus.CONFLICT, "중복되는 경로 순서입니다."),
    UNKNOWN_DELIVERY_AGENT_TYPE(HttpStatus.NOT_FOUND, "잘못된 타입입니다."),
    MAXIMUM_NUMBER_OF_DELIVERY_AGENT(HttpStatus.BAD_REQUEST, "이미 허브에 등록된 배송 담당자가 10명입니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
