package com.lms.eureka.hub.domain.service;

import com.lms.eureka.hub.domain.entity.deliveryAgent.DeliveryAgent;
import com.lms.eureka.hub.domain.entity.deliveryAgent.DeliveryAgentType;
import com.lms.eureka.hub.domain.entity.hub.Hub;
import com.lms.eureka.hub.domain.entity.hubManager.HubManager;
import com.lms.eureka.hub.domain.exception.HubException;
import com.lms.eureka.hub.domain.exception.HubExceptionCase;
import com.lms.eureka.hub.domain.repository.DeliveryAgentRepository;
import com.lms.eureka.hub.domain.repository.HubManagerRepository;
import com.lms.eureka.hub.domain.repository.HubRepository;
import com.lms.eureka.hub.presentation.request.hub.CreateDeliveryAgentRequest;
import com.lms.eureka.hub.presentation.request.hub.CreateHubManagerRequest;
import com.lms.eureka.hub.presentation.request.hub.CreateHubRequest;
import com.lms.eureka.hub.presentation.request.hub.SearchHubRequest;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HubDomainService {

    private static final int MAX_AGENTS_PER_HUB = 10;

    private final HubRepository hubRepository;
    private final HubManagerRepository hubManagerRepository;
    private final DeliveryAgentRepository deliveryAgentRepository;

    @Transactional
    public Hub createHub(CreateHubRequest requestParam, String username) {
        checkDuplicateField(requestParam);
        Hub hub = saveHub(requestParam, username);
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

    private Hub saveHub(CreateHubRequest requestParam, String username) {
        Hub hub = Hub.create(
                requestParam.name(),
                requestParam.address(),
                requestParam.latitude(),
                requestParam.longitude(),
                requestParam.routeIndex(),
                username);
        return hubRepository.save(hub);
    }

    @Transactional(readOnly = true)
    public Hub findHub(UUID hubId) {
        return findHubById(hubId);
    }

    @Transactional(readOnly = true)
    public Hub findHub(String hubName) {
        return findHubByName(hubName);
    }

    @Transactional(readOnly = true)
    public Hub findHub(long routeIndex) {
        return findByRouteIndex(routeIndex);
    }

    private Hub findHubById(UUID hubId) {
        return hubRepository.findByIdAndIsDeleteFalse(hubId)
                .orElseThrow(() -> new HubException(HubExceptionCase.HUB_NOT_FOUND));
    }

    private Hub findHubByName(String hubName) {
        return hubRepository.findByNameAndIsDeleteFalse(hubName)
                .orElseThrow(() -> new HubException(HubExceptionCase.HUB_NOT_FOUND));
    }

    private Hub findByRouteIndex(long midIndex) {
        return hubRepository.findByRouteIndex(midIndex)
                .orElseThrow(() -> new HubException(HubExceptionCase.HUB_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public Page<Hub> searchHub(SearchHubRequest requestParam, Pageable pageable) {
        return hubRepository.searchHub(requestParam, pageable);
    }

    @Transactional
    public Hub deleteHub(UUID hubId, String username) {
        Hub hub = findHubById(hubId);
        hub.delete(username);
        return hub;
    }

    @Transactional
    public HubManager createHubManager(UUID hubId, CreateHubManagerRequest requestParam, String username) {
        Hub hub = findHub(hubId);
        return saveHubManager(hub, requestParam, username);
    }

    private HubManager saveHubManager(Hub hub, CreateHubManagerRequest requestParam, String username) {
        HubManager hubManager = HubManager.create(
                hub,
                requestParam.userId(),
                username);
        return hubManagerRepository.save(hubManager);
    }

    @Transactional
    public DeliveryAgent createDeliveryAgent(UUID hubId, CreateDeliveryAgentRequest requestParam, String username) {
        DeliveryAgentType type = DeliveryAgentType.fromValue(requestParam.type());

        return type == DeliveryAgentType.COMPANY_DELIVERY
                ? createCompanyDeliveryAgent(hubId, requestParam, username)
                : createHubTransferAgent(requestParam, username);
    }

    private DeliveryAgent createCompanyDeliveryAgent(UUID hubId, CreateDeliveryAgentRequest requestParam, String username) {
        validateAgentLimit(hubId);
        Hub hub = findHub(hubId);
        return saveDeliveryAgent(hub, requestParam, DeliveryAgentType.COMPANY_DELIVERY, username);
    }

    private void validateAgentLimit(UUID hubId) {
        long agentCount = deliveryAgentRepository.countByHubId(hubId);
        if (agentCount >= MAX_AGENTS_PER_HUB) {
            throw new HubException(HubExceptionCase.MAXIMUM_NUMBER_OF_DELIVERY_AGENT);
        }
    }

    private DeliveryAgent createHubTransferAgent(CreateDeliveryAgentRequest requestParam, String username) {
        return saveDeliveryAgent(null, requestParam, DeliveryAgentType.HUB_TRANSFER, username);
    }

    private DeliveryAgent saveDeliveryAgent(Hub hub, CreateDeliveryAgentRequest requestParam,
                                            DeliveryAgentType type, String username) {
        DeliveryAgent deliveryAgent = DeliveryAgent.create(
                hub,
                requestParam.userId(),
                type,
                "deliveryAgent@slack.com",
                username);
        return deliveryAgentRepository.save(deliveryAgent);
    }

}
