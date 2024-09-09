package com.lms.eureka.hub.application.service;

import com.lms.eureka.hub.application.dto.HubDto;
import com.lms.eureka.hub.application.dto.mapper.HubMapper;
import com.lms.eureka.hub.domain.model.Hub;
import com.lms.eureka.hub.domain.service.HubDomainService;
import com.lms.eureka.hub.presentation.request.CreateHubRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HubService {

    private final HubDomainService hubDomainService;
    private final HubMapper hubMapper;

    @Transactional
    public HubDto createHub(CreateHubRequest requestParam) {
        Hub hub = hubDomainService.createHub(requestParam);
        return hubMapper.toResponse(hub);
    }

}
