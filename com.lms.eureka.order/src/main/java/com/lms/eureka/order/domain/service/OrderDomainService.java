package com.lms.eureka.order.domain.service;

import com.lms.eureka.order.application.dto.OrderDto;
import com.lms.eureka.order.domain.entity.order.Order;
import com.lms.eureka.order.domain.entity.type.OrderStatus;
import com.lms.eureka.order.domain.exception.OrderException;
import com.lms.eureka.order.domain.exception.OrderExceptionCase;
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

    public OrderDto getOrderByOrderId(UUID orderId) {
        return OrderDto.from(orderRepository.findByOrderId(orderId).orElseThrow(() -> new OrderException(OrderExceptionCase.ORDER_NOT_FOUND)));
    }

    public OrderDto updateOrder(UUID orderId, OrderStatus orderStatus) {
        Order order = orderRepository.findByOrderId(orderId).orElseThrow(() -> new OrderException(OrderExceptionCase.ORDER_NOT_FOUND));
        order.updateOrderStatus(orderStatus);

        return OrderDto.from(orderRepository.findByOrderId(orderId).orElseThrow(() -> new OrderException(OrderExceptionCase.ORDER_NOT_FOUND)));
    }
}
