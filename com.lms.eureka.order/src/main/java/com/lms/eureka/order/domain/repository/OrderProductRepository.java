package com.lms.eureka.order.domain.repository;

import com.lms.eureka.order.domain.entity.OrderProduct.OrderProduct;
import com.lms.eureka.order.domain.entity.order.Order;
import com.lms.eureka.order.infra.repository.orderProduct.OrderProductJpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderProductRepository extends OrderProductJpaRepository {
    List<OrderProduct> saveAll(List<OrderProduct> orderProducts);
    List<OrderProduct> findAllByOrder_OrderId(UUID orderId);
}
