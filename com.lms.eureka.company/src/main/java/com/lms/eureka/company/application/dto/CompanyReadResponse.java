package com.lms.eureka.company.application.dto;

import java.util.UUID;

public record CompanyReadResponse(UUID id, String name) {
}