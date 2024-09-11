package com.lms.eureka.slack.application.service;

import com.lms.eureka.slack.application.dto.SlackDto;
import com.lms.eureka.slack.domain.exception.SlackException;
import com.lms.eureka.slack.domain.exception.SlackExceptionCase;
import com.lms.eureka.slack.domain.repository.SlackRepository;
import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.request.users.UsersLookupByEmailRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.slack.api.methods.response.users.UsersLookupByEmailResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
@Transactional
public class SlackService {
    private final SlackRepository slackRepository;

    @Value("${slack.token}")
    private String TOKEN;

    public void sendCode(SlackDto dto) {
        Slack slack = Slack.getInstance();

        try {
            String channelId = getUserIdByEmail(dto.receiverId());

            MethodsClient methods = slack.methods(TOKEN);

            ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                    .channel(channelId) // 채널명 or 채널 ID
                    .text(dto.content())
                    .build();

            ChatPostMessageResponse response = methods.chatPostMessage(request);

            if (!response.isOk()) {
                throw new SlackException(SlackExceptionCase.SLACK_SEND_FAIL);
            }

            slackRepository.save(dto.toEntity());

        } catch (Exception e) {
            throw new SlackException(SlackExceptionCase.SLACK_SEND_FAIL);
        }
    }

    private String getUserIdByEmail(String email) {
        Slack slack = Slack.getInstance();
        try {
            UsersLookupByEmailRequest request = UsersLookupByEmailRequest.builder()
                    .email(email)
                    .build();
            UsersLookupByEmailResponse response = slack.methods(TOKEN).usersLookupByEmail(request);

            if (response.isOk()) {
                return response.getUser().getId();
            } else {
                throw new SlackException(SlackExceptionCase.SLACK_NOT_FOUND);
            }
        } catch (IOException | SlackApiException e) {
            throw new SlackException(SlackExceptionCase.SLACK_NOT_FOUND);
        }
    }
}
