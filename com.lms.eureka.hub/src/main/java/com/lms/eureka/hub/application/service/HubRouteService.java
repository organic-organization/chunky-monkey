package com.lms.eureka.hub.application.service;

import com.lms.eureka.hub.application.dto.hubRoute.HubRouteDto;
import com.lms.eureka.hub.application.dto.hubRoute.HubRouteMapper;
import com.lms.eureka.hub.domain.entity.hub.Hub;
import com.lms.eureka.hub.domain.entity.hubRoute.HubRoute;
import com.lms.eureka.hub.domain.service.HubDomainService;
import com.lms.eureka.hub.domain.service.HubRouteDomainService;
import com.lms.eureka.hub.presentation.request.hubRoute.CreateHubRouteRequest;
import com.lms.eureka.hub.presentation.request.hubRoute.SearchHubRouteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HubRouteService {

    private final HubRouteDomainService hubRouteDomainService;
    private final HubDomainService hubDomainService;
    private final UserService userService;
    private final HubRouteMapper hubRouteMapper;

    public HubRouteDto createHubRoute(CreateHubRouteRequest requestParam, String username) {
        userService.checkUserExists(username);
        Hub departureHub = hubDomainService.findHub(requestParam.departureHubName());
        Hub arrivalHub = hubDomainService.findHub(requestParam.arrivalHubName());
        HubRoute hubRoute = hubRouteDomainService.createHubRoute(departureHub, arrivalHub, username);
        return hubRouteMapper.toDto(hubRoute);
    }

    public Page<HubRouteDto> searchHubRoute(SearchHubRouteRequest requestParam, Pageable pageable) {
        Page<HubRoute> hubRoutePage = hubRouteDomainService.searchHubRoute(requestParam, pageable);
        return hubRoutePage.map(hubRouteMapper::toDto);
    }

    public HubRouteDto findHubRoute(SearchHubRouteRequest requestParam) {
        Hub departureHub = hubDomainService.findHub(requestParam.departureHubName());
        Hub arrivalHub = hubDomainService.findHub(requestParam.arrivalHubName());
        HubRoute hubRoute = hubRouteDomainService.findHubRoute(departureHub, arrivalHub);
        return hubRouteMapper.toDto(hubRoute);
    }

}

