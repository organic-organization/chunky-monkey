package com.lms.eureka.slack.application.dto;

import com.lms.eureka.slack.domain.model.Slack;
import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder(access = AccessLevel.PRIVATE)
public record SlackDto(
        UUID slackId,
        String receiverId,
        String content,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime updatedAt,
        String updatedBy,
        LocalDateTime deletedAt,
        String deletedBy
) {
    public static SlackDto of(String receiverId, String content) {
        return SlackDto.builder()
                .receiverId(receiverId)
                .content(content)
                .build();
    }

    public Slack toEntity(){
        return Slack.create(receiverId, content);
    }
}
