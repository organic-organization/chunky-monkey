package com.lms.eureka.hub.presentation.exception;

import com.lms.eureka.hub.application.dto.reponse.CommonResponse;
import com.lms.eureka.hub.domain.exception.HubException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class HubExceptionHandler {

    @ExceptionHandler(HubException.class)
    public CommonResponse handleDnaApplicationException(HubException e) {
        return CommonResponse.error(e.getExceptionCase());
    }

}
