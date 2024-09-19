package com.lms.eureka.delivery.application.service;

import com.lms.eureka.delivery.application.client.HubClient;
import com.lms.eureka.delivery.application.dto.HubRouteDto;
import com.lms.eureka.delivery.presentation.request.SearchHubRouteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HubService {
    private final HubClient hubClient;

    public HubRouteDto getHubRoutes(UUID startHubId, UUID endHubId) {
        Pageable pageable = PageRequest.of(0, 50);
        return hubClient.getHubRoutes(new SearchHubRouteRequest(endHubId, startHubId), pageable).getData();
    }

    public List<UUID> getDeliveryManagerList(UUID hubId) {
        return hubClient.getDeliveryManager(hubId).getData();
    }

    public UUID getHubIdByHubManager(String username) {
        return hubClient.getHubIdByHubManager(username).getData();
    }
}
