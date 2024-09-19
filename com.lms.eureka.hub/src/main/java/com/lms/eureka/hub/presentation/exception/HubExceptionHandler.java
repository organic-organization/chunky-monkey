package com.lms.eureka.hub.presentation.exception;

import com.lms.eureka.hub.presentation.reponse.CommonResponse;
import com.lms.eureka.hub.domain.exception.HubException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class HubExceptionHandler {

    @ExceptionHandler(HubException.class)
    public ResponseEntity<CommonResponse> handleDnaApplicationException(HubException e) {
        CommonResponse response = CommonResponse.error(e.getExceptionCase());
        return ResponseEntity
                .status(response.getStatus())
                .body(response);
    }

}
