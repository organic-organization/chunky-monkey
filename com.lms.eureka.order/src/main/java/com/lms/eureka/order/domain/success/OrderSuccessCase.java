package com.lms.eureka.order.domain.success;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderSuccessCase {

    ORDER_CREATE("허브 생성 완료."),
    ORDER_FIND("허브 조회 완료."),
    ORDER_SEARCH("허브 검색 완료."),
    ORDER_DELETE("허브 삭제 완료.")
    ;

    private final String message;

}