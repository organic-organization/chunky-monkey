package com.lms.eureka.hub.application.service;

import com.lms.eureka.hub.application.dto.hubRoute.HubRouteDto;
import com.lms.eureka.hub.application.dto.hubRoute.HubRouteMapper;
import com.lms.eureka.hub.domain.entity.hubRoute.HubRoute;
import com.lms.eureka.hub.domain.repository.HubRouteRepository;
import com.lms.eureka.hub.presentation.request.hubRoute.CreateHubRouteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HubRouteService {

    private final HubRouteRepository hubRouteRepository;
    private final HubService hubService;
    private final UserService userService;
    private final HubRouteMapper hubRouteMapper;

    @Transactional
    public HubRouteDto createHubRoute(CreateHubRouteRequest requestParam, String username) {
        hubService.checkHubExists(requestParam.departureHubId());
        hubService.checkHubExists(requestParam.arrivalHubId());
        userService.checkUserExists(username);
        HubRoute hubRoute = saveHubRoute(requestParam, username);
        return hubRouteMapper.toDto(hubRoute);
    }

    private HubRoute saveHubRoute(CreateHubRouteRequest requestParam, String username) {
        HubRoute hubRoute = HubRoute.create(
                requestParam.departureHubId(),
                requestParam.arrivalHubId(),
                requestParam.duration(),
                username);
        hubRouteRepository.save(hubRoute);
        return hubRoute;
    }

}
