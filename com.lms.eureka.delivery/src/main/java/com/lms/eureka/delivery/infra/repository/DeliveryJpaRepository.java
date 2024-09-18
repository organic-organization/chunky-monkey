package com.lms.eureka.delivery.infra.repository;

import com.lms.eureka.delivery.domain.entity.delivery.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeliveryJpaRepository extends JpaRepository<Delivery, UUID> {
}
