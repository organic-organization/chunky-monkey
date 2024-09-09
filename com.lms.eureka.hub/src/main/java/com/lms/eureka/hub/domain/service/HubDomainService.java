package com.lms.eureka.hub.domain.service;

import com.lms.eureka.hub.domain.model.Hub;
import com.lms.eureka.hub.domain.repository.HubRepository;
import com.lms.eureka.hub.presentation.request.CreateHubRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HubDomainService {

    private final HubRepository hubRepository;

    public Hub createHub(CreateHubRequest requestParam) {
        Hub hub = Hub.createHub(
                requestParam.name(),
                requestParam.address(),
                requestParam.latitude(),
                requestParam.longitude(),
                requestParam.routeIndex());
        hubRepository.save(hub);
        return hub;
    }

}
