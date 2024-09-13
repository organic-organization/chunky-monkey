package com.lms.eureka.order.presentation.exception;

import com.lms.eureka.order.domain.exception.OrderException;
import com.lms.eureka.order.presentation.reponse.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class OrderExceptionHandler {

    @ExceptionHandler(OrderException.class)
    public ResponseEntity<CommonResponse> handleDnaApplicationException(OrderException e) {
        CommonResponse response = CommonResponse.error(e.getExceptionCase());
        return ResponseEntity
                .status(response.getStatus())
                .body(response);
    }

}
