package com.lms.eureka.hub.application.dto.hubRoute;

import java.time.LocalDateTime;
import java.util.UUID;

public record HubRouteDto(UUID id,
                          UUID departureHubId,
                          UUID arrivalHubId,
                          long duration,
                          Boolean isDelete,
                          LocalDateTime createdAt,
                          String createdBy,
                          LocalDateTime updatedAt,
                          String updatedBy,
                          LocalDateTime deletedAt,
                          String deletedBy) {
}
