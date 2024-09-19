package com.lms.eureka.company.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CompanyUpdateResponse(UUID id, String name, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
