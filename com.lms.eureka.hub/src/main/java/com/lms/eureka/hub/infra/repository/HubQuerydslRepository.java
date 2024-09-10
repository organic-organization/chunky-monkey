package com.lms.eureka.hub.infra.repository;

import com.lms.eureka.hub.domain.entity.hub.Hub;
import com.lms.eureka.hub.presentation.request.SearchHubRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HubQuerydslRepository {
    Page<Hub> searchHub(SearchHubRequest requestParam, Pageable pageable);
}
