package com.lms.eureka.hub.domain.repository;

import com.lms.eureka.hub.domain.entity.hub.Hub;
import com.lms.eureka.hub.presentation.request.SearchHubRequest;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HubRepository {
    Hub save(Hub hub);
    Optional<Hub> findById(UUID hubId);
    Page<Hub> searchHub(SearchHubRequest requestParam, Pageable pageable);
    Optional<Hub> findByName(String name);
    Optional<Hub> findByAddress(String address);
    Optional<Hub> findByRouteIndex(long routeIndex);
}
