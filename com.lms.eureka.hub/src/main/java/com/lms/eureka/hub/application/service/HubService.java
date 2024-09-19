package com.lms.eureka.hub.application.service;

import com.lms.eureka.hub.application.dto.hub.HubDto;
import com.lms.eureka.hub.application.dto.hub.HubManagerDto;
import com.lms.eureka.hub.application.dto.hub.HubManagerMapper;
import com.lms.eureka.hub.application.dto.hub.HubMapper;
import com.lms.eureka.hub.domain.entity.hub.Hub;
import com.lms.eureka.hub.domain.entity.hub.HubManager;
import com.lms.eureka.hub.domain.service.HubDomainService;
import com.lms.eureka.hub.presentation.request.hub.CreateHubManagerRequest;
import com.lms.eureka.hub.presentation.request.hub.CreateHubRequest;
import com.lms.eureka.hub.presentation.request.hub.SearchHubRequest;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HubService {

    private final HubDomainService hubDomainService;
    private final HubMapper hubMapper;
    private final UserService userService;
    private final HubManagerMapper hubManagerMapper;

    public HubDto createHub(CreateHubRequest requestParam, String username) {
        userService.checkUserExists(username);
        Hub hub = hubDomainService.createHub(requestParam, username);
        return hubMapper.toDto(hub);
    }

    public HubDto findHub(UUID hubId) {
        Hub hub = hubDomainService.findHub(hubId);
        return hubMapper.toDto(hub);
    }

    public Page<HubDto> searchHub(SearchHubRequest requestParam, Pageable pageable) {
        Page<Hub> hubPage = hubDomainService.searchHub(requestParam, pageable);
        return hubPage.map(hubMapper::toDto);
    }

    public HubDto deleteHub(UUID hubId, String username) {
        userService.checkUserExists(username);
        Hub hub = hubDomainService.deleteHub(hubId, username);
        return hubMapper.toDto(hub);
    }

    public HubManagerDto createHubManager(UUID hubId, CreateHubManagerRequest requestParam, String username) {
        userService.checkUserExists(username);
        // requestParam의 유저 권한 체크 필요
        HubManager hubManager = hubDomainService.createHubManager(hubId, requestParam, username);
        return hubManagerMapper.toDto(hubManager);
    }

}
