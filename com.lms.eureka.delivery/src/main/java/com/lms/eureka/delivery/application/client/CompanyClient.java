package com.lms.eureka.delivery.application.client;

import com.lms.eureka.delivery.presentation.reponse.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "company-service")
public interface CompanyClient {

    @GetMapping("api/company/{companyId}")
    CommonResponse<UUID> getCompany(@PathVariable("companyId") UUID companyId);
}
