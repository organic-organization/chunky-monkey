package com.lms.eureka.slack.presentation.controller;

import com.lms.eureka.slack.application.service.SlackService;
import com.lms.eureka.slack.presentation.request.SendCodeRequestDto;
import com.lms.eureka.slack.presentation.response.CommonResponse;
import com.slack.api.methods.SlackApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/slack")
public class SlackController {
    private final SlackService slackService;

    @PostMapping("/send/code")
    public CommonResponse<Void> sendCode(@RequestBody SendCodeRequestDto request){
        slackService.sendCode(request.toDto());
        return CommonResponse.success(null);
    }
}
