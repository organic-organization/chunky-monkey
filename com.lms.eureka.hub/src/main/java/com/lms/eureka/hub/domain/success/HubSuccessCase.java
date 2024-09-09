package com.lms.eureka.hub.domain.success;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HubSuccessCase {

    HUB_CREATE("허브 생성 완료."),
    HUB_FIND("허브 조회 완료.");

    private final String message;

}