package com.lms.eureka.user.presentation.request;

public record SlackSendRequestDto(
        String receiverSlackId,
        String message
) {
    public static SlackSendRequestDto create(String slackId, String message) {
        return new SlackSendRequestDto(slackId, message);
    }
}
