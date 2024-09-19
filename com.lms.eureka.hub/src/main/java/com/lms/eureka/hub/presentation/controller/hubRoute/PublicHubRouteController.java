package com.lms.eureka.hub.presentation.controller.hubRoute;

import static com.lms.eureka.hub.domain.success.HubSuccessCase.HUB_ROUTE_SEARCH;

import com.lms.eureka.hub.application.dto.hubRoute.HubRouteDto;
import com.lms.eureka.hub.application.service.HubRouteService;
import com.lms.eureka.hub.presentation.reponse.CommonResponse;
import com.lms.eureka.hub.presentation.request.hubRoute.SearchHubRouteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public/hub-routes")
public class PublicHubRouteController {

    private final HubRouteService hubRouteService;

    @GetMapping("/search")
    public CommonResponse searchHubRoute(@ModelAttribute SearchHubRouteRequest requestParam, Pageable pageable) {
        Page<HubRouteDto> hubRouteDtoPage = hubRouteService.searchHubRoute(requestParam, pageable);
        return CommonResponse.success(HUB_ROUTE_SEARCH.getMessage(), hubRouteDtoPage);
    }

}
