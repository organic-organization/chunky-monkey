package com.lms.eureka.order.domain.service;

import com.lms.eureka.order.application.dto.OrderDto;
import com.lms.eureka.order.domain.entity.order.Order;
import com.lms.eureka.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderDomainService {
    private final OrderRepository orderRepository;

    public OrderDto createOrder(String username, OrderDto dto) {
        Order order = orderRepository.save(dto.toEntity(username));
        return OrderDto.from(order);
    }

    public Page<OrderDto> getOrders(String companyId, Pageable pageable) {
        return orderRepository.findAllByRecipientCompanyId(UUID.fromString(companyId), pageable).map(OrderDto::from);
    }

    public OrderDto getOrderByOrderId(String orderId) {
        return OrderDto.from(orderRepository.findByOrderId(UUID.fromString(orderId)));
    }
}
