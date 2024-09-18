package com.lms.eureka.delivery.presentation.exception;


import com.lms.eureka.delivery.domain.exception.DeliveryException;
import com.lms.eureka.delivery.presentation.reponse.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class DeliveryExceptionHandler {

    @ExceptionHandler(DeliveryException.class)
    public ResponseEntity<CommonResponse> handleDnaApplicationException(DeliveryException e) {
        CommonResponse response = CommonResponse.error(e.getExceptionCase());
        return ResponseEntity
                .status(response.getStatus())
                .body(response);
    }

}
