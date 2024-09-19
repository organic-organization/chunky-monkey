package com.lms.eureka.slack.presentation.exception;

import com.lms.eureka.slack.domain.exception.SlackException;
import com.lms.eureka.slack.presentation.response.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class SlackExceptionHandler {

    @ExceptionHandler(SlackException.class)
    public ResponseEntity<CommonResponse> handleDnaApplicationException(SlackException e) {
        CommonResponse response = CommonResponse.error(e.getExceptionCase());
        return ResponseEntity
                .status(response.getStatus())
                .body(response);
    }
}