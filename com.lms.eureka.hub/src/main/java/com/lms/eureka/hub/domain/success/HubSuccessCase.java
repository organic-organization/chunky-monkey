package com.lms.eureka.hub.domain.success;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HubSuccessCase {

    HUB_CREATE("허브 생성 완료."),
    HUB_FIND("허브 조회 완료."),
    HUB_SEARCH("허브 검색 완료."),
    HUB_DELETE("허브 삭제 완료."),
    HUB_ROUTE_CREATE("이동 정보 생성 완료."),
    HUB_ROUTE_SEARCH("이동 정보 검색 완료.");

    private final String message;

}