package com.lms.eureka.hub.presentation.controller.hubRoute;

import static com.lms.eureka.hub.domain.success.HubSuccessCase.HUB_ROUTE_CREATE;

import com.lms.eureka.hub.application.dto.hubRoute.HubRouteDto;
import com.lms.eureka.hub.application.service.HubRouteService;
import com.lms.eureka.hub.presentation.reponse.CommonResponse;
import com.lms.eureka.hub.presentation.request.hubRoute.CreateHubRouteRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthHubRouteController {

    private final HubRouteService hubRouteService;

    @PostMapping("/master/hub-routes")
    public CommonResponse createHubRoute(@Valid @RequestBody CreateHubRouteRequest requestParam,
                                    @RequestHeader(value = "username", required = true) String username) {
        HubRouteDto hubRouteDto = hubRouteService.createHubRoute(requestParam, username);
        return CommonResponse.success(HUB_ROUTE_CREATE.getMessage(), hubRouteDto);
    }

}
