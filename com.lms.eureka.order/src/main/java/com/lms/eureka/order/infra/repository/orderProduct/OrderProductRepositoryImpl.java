package com.lms.eureka.order.infra.repository.orderProduct;

import com.lms.eureka.order.domain.repository.OrderProductRepository;

public interface OrderProductRepositoryImpl extends
        OrderProductRepository,
        OrderProductJpaRepository
//       , OrderProductQuerydslRepository
{
}
