package com.lms.eureka.hub.domain.repository;

import com.lms.eureka.hub.domain.entity.hubRoute.HubRoute;
import com.lms.eureka.hub.presentation.request.hubRoute.SearchHubRouteRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HubRouteRepository {
    HubRoute save(HubRoute hubRoute);
    Page<HubRoute> searchHubRoute(SearchHubRouteRequest requestParam, Pageable pageable);

}
