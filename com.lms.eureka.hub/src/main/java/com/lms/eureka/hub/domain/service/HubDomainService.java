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
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HubDomainService {

    private final HubRepository hubRepository;

    @Transactional
    public Hub createHub(CreateHubRequest requestParam, String username) {
        checkDuplicateField(requestParam);
        Hub hub = Hub.create(
                requestParam.name(),
                requestParam.address(),
                requestParam.latitude(),
                requestParam.longitude(),
                requestParam.routeIndex(),
                username);
        hubRepository.save(hub);
        return hub;
    }

    private void checkDuplicateField(CreateHubRequest requestParam) {
        if (hubRepository.findByName(requestParam.name()).isPresent()){
            throw new HubException(HubExceptionCase.DUPLICATE_NAME);
        }
        if (hubRepository.findByAddress(requestParam.address()).isPresent()) {
            throw new HubException(HubExceptionCase.DUPLICATE_ADDRESS);
        }
        if (hubRepository.findByRouteIndex(requestParam.routeIndex()).isPresent()) {
            throw new HubException(HubExceptionCase.DUPLICATE_ROUTE_INDEX);
        }
    }

    @Transactional(readOnly = true)
    public Hub findHub(UUID hubId) {
        return hubRepository.findById(hubId)
                .orElseThrow(() -> new HubException(HubExceptionCase.HUB_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public Page<Hub> searchHub(SearchHubRequest requestParam, Pageable pageable) {
        return hubRepository.searchHub(requestParam, pageable);
    }

    @Transactional
    public Hub deleteHub(UUID hubId, String username) {
        Hub hub = findHub(hubId);
        hub.delete(username);
        return hub;
    }

}
