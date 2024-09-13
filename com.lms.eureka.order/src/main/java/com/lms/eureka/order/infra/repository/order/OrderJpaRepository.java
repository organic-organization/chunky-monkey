package com.lms.eureka.order.infra.repository.order;

import com.lms.eureka.order.domain.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderJpaRepository extends JpaRepository<Order, UUID> {
}
