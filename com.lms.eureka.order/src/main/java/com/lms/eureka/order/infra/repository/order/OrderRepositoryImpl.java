package com.lms.eureka.order.infra.repository.order;

import com.lms.eureka.order.domain.repository.OrderRepository;

public interface OrderRepositoryImpl extends
        OrderRepository,
        OrderJpaRepository
        , OrderQuerydslRepository
{
}
