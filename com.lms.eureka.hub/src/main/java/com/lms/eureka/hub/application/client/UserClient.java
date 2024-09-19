package com.lms.eureka.hub.application.client;

import com.lms.eureka.hub.presentation.reponse.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("api/user/{username}")
    CommonResponse getUser(@PathVariable("username") String username);

}
