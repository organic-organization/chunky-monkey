package com.lms.eureka.company.presentation.request;

import java.util.UUID;

public record CompanyProductCreateRequest(String name, Integer price, Integer stock) {
}
