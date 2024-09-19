package com.lms.eureka.delivery.domain.repository.deliveryRoute;

import com.lms.eureka.delivery.domain.entity.deliveryRoute.DeliveryRoute;
import com.lms.eureka.delivery.infra.repository.deliveryRoute.QueryDslDeliveryRouteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeliveryRouteRepository extends QueryDslDeliveryRouteRepository, JpaRepository<DeliveryRoute, UUID> {
    Page<DeliveryRoute> findAllByDeliveryManagerId(UUID deliveryManagerId, Pageable pageable);
}
