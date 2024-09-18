package com.lms.eureka.delivery.application.service;

import com.lms.eureka.delivery.application.client.HubClient;
import com.lms.eureka.delivery.application.dto.HubRouteDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HubService {
    private final HubClient hubClient;

    public List<HubRouteDto> getHubRoutes(Map<UUID, UUID> companyHubMap){
        Pageable pageable = PageRequest.of(0, 50);
        return hubClient.getHubRoutes(pageable).getData();
    }
}
