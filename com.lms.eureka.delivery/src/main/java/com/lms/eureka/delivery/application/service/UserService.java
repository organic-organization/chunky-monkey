package com.lms.eureka.delivery.application.service;

import com.lms.eureka.delivery.application.client.UserClient;
import com.lms.eureka.delivery.domain.exception.DeliveryException;
import com.lms.eureka.delivery.domain.exception.DeliveryExceptionCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserClient userClient;

    public void checkUserExists(String username) {
        if (!userClient.getUser(username).getStatus().equals(HttpStatus.OK)) {
            throw new DeliveryException(DeliveryExceptionCase.USER_NOT_FOUND);
        }
    }

}
