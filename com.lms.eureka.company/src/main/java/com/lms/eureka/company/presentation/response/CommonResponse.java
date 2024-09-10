package com.lms.eureka.company.presentation.response;

import com.fasterxml.jackson.annotation.JsonInclude;
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

    public static <T> CommonResponse<T> success(HttpStatus status, String message, T data) {
        return CommonResponse.<T>builder()
                .status(status)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> CommonResponse<T> success(HttpStatus status, T data) {
        return CommonResponse.<T>builder()
                .status(status)
                .data(data)
                .build();
    }

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

    public static <T> CommonResponse<T> failure(String message) {
        return CommonResponse.<T>builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(message)
                .build();
    }

}
