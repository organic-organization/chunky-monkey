package com.lms.eureka.delivery.infra.repository;

import com.lms.eureka.delivery.domain.entity.deliveryRoute.DeliveryRoute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeliveryRouteJpaRepository extends JpaRepository<DeliveryRoute, UUID> {
}
