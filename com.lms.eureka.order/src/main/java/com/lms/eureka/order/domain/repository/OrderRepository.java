package com.lms.eureka.order.domain.repository;

import com.lms.eureka.order.domain.entity.order.Order;

public interface OrderRepository {
    Order save(Order order);
}
