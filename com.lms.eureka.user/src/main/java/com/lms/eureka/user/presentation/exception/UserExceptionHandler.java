package com.lms.eureka.user.presentation.exception;

import com.lms.eureka.user.domain.exception.UserException;
import com.lms.eureka.user.presentation.response.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class UserExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<CommonResponse> handleDnaApplicationException(UserException e) {
        CommonResponse response = CommonResponse.error(e.getExceptionCase());
        return ResponseEntity
                .status(response.getStatus())
                .body(response);
    }
}