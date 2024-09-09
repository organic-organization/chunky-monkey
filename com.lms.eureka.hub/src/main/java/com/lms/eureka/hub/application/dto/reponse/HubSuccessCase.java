package com.lms.eureka.hub.application.dto.reponse;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HubSuccessCase {

    HUB_CREATE("허브 생성 완료.");

    private final String message;

}