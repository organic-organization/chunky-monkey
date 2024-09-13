package com.lms.eureka.order.domain.service;

import com.lms.eureka.order.application.dto.OrderDto;
import com.lms.eureka.order.domain.entity.order.Order;
import com.lms.eureka.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderDomainService {
    private final OrderRepository orderRepository;

    public OrderDto createOrder(String username, OrderDto dto) {
        Order order = orderRepository.save(dto.toEntity(username));
        return OrderDto.from(order);
    }
}
