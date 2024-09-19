package com.lms.eureka.hub.application.dto.hubManager;

import java.time.LocalDateTime;
import java.util.UUID;

public record HubManagerDto(UUID id,
                            UUID hubId,
                            Long userId,
                            Boolean isDelete,
                            LocalDateTime createdAt,
                            String createdBy,
                            LocalDateTime updatedAt,
                            String updatedBy,
                            LocalDateTime deletedAt,
                            String deletedBy) {
}
