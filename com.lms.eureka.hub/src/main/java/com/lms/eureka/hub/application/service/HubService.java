package com.lms.eureka.hub.application.service;

import com.lms.eureka.hub.application.dto.hub.HubDto;
import com.lms.eureka.hub.application.dto.hub.HubMapper;
import com.lms.eureka.hub.domain.entity.hub.Hub;
import com.lms.eureka.hub.domain.exception.HubException;
import com.lms.eureka.hub.domain.exception.HubExceptionCase;
import com.lms.eureka.hub.domain.repository.HubRepository;
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
public class HubService {

    private final HubRepository hubRepository;
    private final HubMapper hubMapper;
    private final UserService userService;

    @Transactional
    public HubDto createHub(CreateHubRequest requestParam, String username) {
        userService.checkUserExists(username);
        checkDuplicateField(requestParam);
        Hub hub = saveHub(requestParam, username);
        return hubMapper.toDto(hub);
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
        hubRepository.save(hub);
        return hub;
    }

    @Transactional(readOnly = true)
    public HubDto findHub(UUID hubId) {
        Hub hub = findHubById(hubId);
        return hubMapper.toDto(hub);
    }

    private Hub findHubById(UUID hubId) {
        return hubRepository.findByIdAndIsDeleteFalse(hubId)
                .orElseThrow(() -> new HubException(HubExceptionCase.HUB_NOT_FOUND));
    }

    public boolean checkHubExists(UUID hubId) {
        Hub hub = findHubById(hubId);
        return hub != null;
    }

    @Transactional(readOnly = true)
    public Page<HubDto> searchHub(SearchHubRequest requestParam, Pageable pageable) {
        Page<Hub> hubPage = hubRepository.searchHub(requestParam, pageable);
        return hubPage.map(hubMapper::toDto);
    }

    @Transactional
    public HubDto deleteHub(UUID hubId, String username) {
        userService.checkUserExists(username);
        Hub hub = findHubById(hubId);
        hub.delete(username);
        return hubMapper.toDto(hub);
    }

}
