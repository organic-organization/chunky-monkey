package com.lms.eureka.hub.presentation.controller.hubRoute;

import static com.lms.eureka.hub.domain.success.HubSuccessCase.HUB_ROUTE_FIND;

import com.lms.eureka.hub.application.dto.hubRoute.HubRouteDto;
import com.lms.eureka.hub.application.service.HubRouteService;
import com.lms.eureka.hub.presentation.reponse.CommonResponse;
import com.lms.eureka.hub.presentation.request.hubRoute.SearchHubRouteRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hub-routes")
public class FeignClientHubRouteController {

    private final HubRouteService hubRouteService;

    @GetMapping
    public CommonResponse findHubRoute(@Valid @ModelAttribute SearchHubRouteRequest requestParam) {
        HubRouteDto hubRouteDto = hubRouteService.findHubRoute(requestParam);
        return CommonResponse.success(HUB_ROUTE_FIND.getMessage(), hubRouteDto);
    }
    
}
