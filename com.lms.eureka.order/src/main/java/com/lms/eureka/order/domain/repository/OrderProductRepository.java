package com.lms.eureka.order.domain.repository;

import com.lms.eureka.order.domain.entity.OrderProduct.OrderProduct;

import java.util.List;

public interface OrderProductRepository {
    List<OrderProduct> saveAll(List<OrderProduct> orderProducts);
}
