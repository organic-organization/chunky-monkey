package com.lms.eureka.hub.infra.repository.hubRoute;

import com.lms.eureka.hub.domain.entity.hubRoute.HubRoute;
import com.lms.eureka.hub.presentation.request.hubRoute.SearchHubRouteRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HubRouteQuerydslRepository {
    Page<HubRoute> searchHubRoute(SearchHubRouteRequest requestParam, Pageable pageable);

}
