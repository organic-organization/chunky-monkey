package com.lms.eureka.slack.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SlackExceptionCase {

    SLACK_SEND_FAIL(HttpStatus.BAD_REQUEST, "메세지 보내는데 실패하였습니다."),
    SLACK_NOT_FOUND(HttpStatus.BAD_REQUEST, "해당하는 사용자가 없습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;

}
