package com.lms.eureka.order.domain.repository;

import com.lms.eureka.order.domain.entity.order.Order;
import com.lms.eureka.order.infra.repository.order.OrderJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends OrderJpaRepository {
    Page<Order> findAllByRecipientCompanyId(UUID recipientCompanyId, Pageable pageable);

    Optional<Order> findByOrderId(UUID orderId);
}
