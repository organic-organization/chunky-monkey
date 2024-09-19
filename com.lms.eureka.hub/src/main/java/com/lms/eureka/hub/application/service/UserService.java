package com.lms.eureka.hub.application.service;

import com.lms.eureka.hub.application.client.UserClient;
import com.lms.eureka.hub.domain.exception.HubException;
import com.lms.eureka.hub.domain.exception.HubExceptionCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserClient userClient;

    public void checkUserExists(String username) {
        if (!userClient.getUser(username).getStatus().equals(HttpStatus.OK)) {
            throw new HubException(HubExceptionCase.USER_NOT_FOUND);
        }
    }

}
