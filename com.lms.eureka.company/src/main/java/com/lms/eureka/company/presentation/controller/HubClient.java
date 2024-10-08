package com.lms.eureka.company.presentation.controller;

import com.lms.eureka.company.presentation.response.CommonResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "hub-service")
public interface HubClient {

    @GetMapping("/api/public/hubs/{hubId}")
    CommonResponse findHub(@Valid @PathVariable("hubId") UUID hubId);
}