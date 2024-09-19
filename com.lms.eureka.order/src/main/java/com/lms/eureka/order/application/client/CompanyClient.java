package com.lms.eureka.order.application.client;

import com.lms.eureka.order.presentation.reponse.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "company-service")
public interface CompanyClient {
    @GetMapping("api/company/{username}")
    CommonResponse<String> getCompany(@PathVariable("username") String username);
}
