package com.lms.eureka.hub.presentation.reponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lms.eureka.hub.domain.exception.HubExceptionCase;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> {

    private HttpStatus status;
    private String message;
    private T data;

    public static <T> CommonResponse<T> success(String message, T data) {
        return CommonResponse.<T>builder()
                .status(HttpStatus.OK)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> CommonResponse<T> success(String message) {
        return CommonResponse.<T>builder()
                .status(HttpStatus.OK)
                .message(message)
                .build();
    }

    public static <T> CommonResponse<T> error(HubExceptionCase exceptionCase) {
        return CommonResponse.<T>builder()
                .status(exceptionCase.getHttpStatus())
                .message(exceptionCase.getMessage())
                .build();
    }

}
