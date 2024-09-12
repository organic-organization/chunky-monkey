package com.lms.eureka.company.application.dto;

import java.util.UUID;

public record CompanyManagerReadResponse(UUID id, UUID companyId, Long userId) {
}
