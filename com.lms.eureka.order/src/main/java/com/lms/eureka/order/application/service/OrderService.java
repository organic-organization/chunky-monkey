package com.lms.eureka.order.application.service;

import com.lms.eureka.order.application.dto.OrderDto;
import com.lms.eureka.order.application.dto.OrderProductDto;
import com.lms.eureka.order.domain.service.OrderDomainService;
import com.lms.eureka.order.domain.service.OrderProductDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderDomainService orderDomainService;
    private final OrderProductDomainService orderProductDomainService;
    private final UserService userService;

    public void createOrder(String username, OrderDto dto, List<OrderProductDto> list) {
//        userService.checkUserExists(username);
        OrderDto saveOrderDto = orderDomainService.createOrder(username, dto);
        orderProductDomainService.createOrderProduct(username, saveOrderDto, list);
    }
}
