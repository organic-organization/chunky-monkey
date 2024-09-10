package com.lms.eureka.hub.domain.service;

import com.lms.eureka.hub.domain.exception.HubException;
import com.lms.eureka.hub.domain.exception.HubExceptionCase;
import com.lms.eureka.hub.domain.entity.hub.Hub;
import com.lms.eureka.hub.domain.repository.HubRepository;
import com.lms.eureka.hub.presentation.request.CreateHubRequest;
import com.lms.eureka.hub.presentation.request.SearchHubRequest;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Hub findHub(UUID hubId) {
        return hubRepository.findById(hubId)
                .orElseThrow(() -> new HubException(HubExceptionCase.HUB_NOT_FOUND));
    }

    public Page<Hub> searchHub(SearchHubRequest requestParam, Pageable pageable) {
        return hubRepository.searchHub(requestParam, pageable);
    }

}
