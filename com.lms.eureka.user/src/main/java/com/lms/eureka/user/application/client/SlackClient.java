package com.lms.eureka.user.application.client;

import com.lms.eureka.user.presentation.request.SlackSendRequestDto;
import com.lms.eureka.user.presentation.response.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "slack-service")
public interface SlackClient {
    @PostMapping("api/slack/send/code")
    CommonResponse sendPasswordCodeMessage(@RequestBody SlackSendRequestDto dto);
}
