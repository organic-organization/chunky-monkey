package com.lms.eureka.company.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CompanyReadResponse(UUID id, String name, LocalDateTime createdAt) {
}