package com.lms.eureka.company.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CompanyProductReadResponse(UUID id, String name, Integer price, Integer stock, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
