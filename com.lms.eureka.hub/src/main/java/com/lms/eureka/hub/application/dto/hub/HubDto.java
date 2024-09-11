package com.lms.eureka.hub.application.dto.hub;

import java.time.LocalDateTime;
import java.util.UUID;

public record HubDto(UUID id, String name, String address, double latitude, double longitude, long routeIndex,
                     LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt,
                     String updatedBy, LocalDateTime deletedAt, String deletedBy) {
}
