package com.lms.eureka.company.presentation.request;

import java.util.UUID;

public record CompanyCreateRequest(String name, String address, String companyType, UUID hubId) {
}