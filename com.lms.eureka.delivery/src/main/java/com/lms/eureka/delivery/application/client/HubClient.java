package com.lms.eureka.delivery.application.client;

import com.lms.eureka.delivery.application.dto.HubRouteDto;
import com.lms.eureka.delivery.presentation.reponse.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "hub-service")
public interface HubClient {

    @GetMapping("/api/public/hub-routes")
    CommonResponse<List<HubRouteDto>> getHubRoutes(Pageable pageable);
}
