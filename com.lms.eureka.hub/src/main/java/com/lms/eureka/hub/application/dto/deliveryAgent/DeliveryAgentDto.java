package com.lms.eureka.hub.application.dto.deliveryAgent;

import java.time.LocalDateTime;
import java.util.UUID;

public record DeliveryAgentDto(UUID id,
                               UUID hubId,
                               Long userId,
                               String type,
                               String slackId,
                               Boolean isDelete,
                               LocalDateTime createdAt,
                               String createdBy,
                               LocalDateTime updatedAt,
                               String updatedBy,
                               LocalDateTime deletedAt,
                               String deletedBy) {
}
