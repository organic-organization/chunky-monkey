package com.lms.eureka.hub.infra.repository.hubRoute;

import com.lms.eureka.hub.domain.entity.hubRoute.HubRoute;
import com.lms.eureka.hub.domain.repository.HubRouteRepository;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HubRouteJpaRepository extends JpaRepository<HubRoute, UUID>, HubRouteRepository {
}
