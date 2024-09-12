package com.lms.eureka.slack.presentation.request;

import com.lms.eureka.slack.application.dto.SlackDto;

public record SendCodeRequestDto(
        String receiverSlackId,
        String message
) {
    public SlackDto toDto(){
        return SlackDto.of(receiverSlackId, message);
    }
}
