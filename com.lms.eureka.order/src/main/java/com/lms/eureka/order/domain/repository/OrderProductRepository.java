package com.lms.eureka.order.domain.repository;

import com.lms.eureka.order.domain.entity.OrderProduct.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderProductRepository extends JpaRepository<OrderProduct, UUID> {
    List<OrderProduct> findAllByOrder_OrderId(UUID orderId);
}
