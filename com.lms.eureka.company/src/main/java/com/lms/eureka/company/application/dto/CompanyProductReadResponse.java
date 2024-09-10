package com.lms.eureka.company.application.dto;

import java.util.UUID;

public record CompanyProductReadResponse(UUID id, String name, Integer price, Integer stock) {
}
