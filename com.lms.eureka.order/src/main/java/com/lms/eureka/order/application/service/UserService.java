package com.lms.eureka.order.application.service;

import com.lms.eureka.order.application.client.UserClient;
import com.lms.eureka.order.domain.exception.OrderException;
import com.lms.eureka.order.domain.exception.OrderExceptionCase;
import com.lms.eureka.order.presentation.reponse.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserClient userClient;

    public void checkUserExists(String username) {
        if (!userClient.getUser(username).getStatus().equals(HttpStatus.OK)) {
            throw new OrderException(OrderExceptionCase.USER_NOT_FOUND);
        }
    }

    public String getUserSlackId(String username) {
        CommonResponse<String> response = userClient.getSlackId(username);

        if (!response.getStatus().equals(HttpStatus.OK)) {
            throw new OrderException(OrderExceptionCase.USER_NOT_FOUND);
        }

        return response.getData();
    }

}
