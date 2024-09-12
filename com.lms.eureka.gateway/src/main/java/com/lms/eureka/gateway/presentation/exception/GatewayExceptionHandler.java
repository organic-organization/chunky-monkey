package com.lms.eureka.gateway.presentation.exception;

import com.lms.eureka.gateway.domain.exception.GatewayException;
import com.lms.eureka.gateway.presentation.response.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GatewayExceptionHandler {

    @ExceptionHandler(GatewayException.class)
    public ResponseEntity<CommonResponse> handleDnaApplicationException(GatewayException e) {
        CommonResponse response = CommonResponse.error(e.getExceptionCase());
        return ResponseEntity
                .status(response.getStatus())
                .body(response);
    }
}