package com.lms.eureka.user.presentation.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lms.eureka.user.domain.exception.UserExceptionCase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    public static <T> CommonResponse<T> success(T data) {
        return CommonResponse.<T>builder()
                .status(HttpStatus.OK)
                .data(data)
                .build();
    }

    public static <T> CommonResponse<T> success(String message) {
        return CommonResponse.<T>builder()
                .status(HttpStatus.OK)
                .message(message)
                .build();
    }

    public static <T> CommonResponse<T> error(UserExceptionCase exceptionCase) {
        return CommonResponse.<T>builder()
                .status(exceptionCase.getHttpStatus())
                .message(exceptionCase.getMessage())
                .build();
    }

}
