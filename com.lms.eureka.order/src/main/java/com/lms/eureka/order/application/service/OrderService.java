package com.lms.eureka.order.application.service;

import com.lms.eureka.order.application.client.CompanyClient;
import com.lms.eureka.order.application.client.DeliveryClient;
import com.lms.eureka.order.application.dto.OrderDto;
import com.lms.eureka.order.application.dto.OrderProductDto;
import com.lms.eureka.order.domain.service.OrderDomainService;
import com.lms.eureka.order.domain.service.OrderProductDomainService;
import com.lms.eureka.order.presentation.reponse.OrderDetailResponseDto;
import com.lms.eureka.order.presentation.reponse.OrderResponseDto;
import com.lms.eureka.order.presentation.request.CreateDeliveryRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderDomainService orderDomainService;
    private final OrderProductDomainService orderProductDomainService;
    private final UserService userService;
    private final DeliveryService deliveryService;
    private final CompanyService companyService;

    public void createOrder(String username, OrderDto dto, List<OrderProductDto> list) {
        userService.checkUserExists(username);

        OrderDto saveOrderDto = orderDomainService.createOrder(username, dto);
        orderProductDomainService.createOrderProduct(username, saveOrderDto, list);

        String recipientSlackId = userService.getUserSlackId(username);

        // delivery 호출
        deliveryService.createDelivery(CreateDeliveryRequestDto.from(saveOrderDto, recipientSlackId));
    }

    public Page<OrderResponseDto> getOrders(String username, Pageable pageable) {
        // company 호출 - 속해있는 company 조회
        String companyId = companyService.getCompany(username);

        Page<OrderDto> orders = orderDomainService.getOrders(companyId, pageable);

        return orders.map(OrderResponseDto::from);
    }

    public OrderDetailResponseDto getOrderByOrderId(String orderId) {
        OrderDto order = orderDomainService.getOrderByOrderId(orderId);
        List<OrderProductDto> orderProducts = orderProductDomainService.getProductListByOrderId(order.orderId());

        return OrderDetailResponseDto.from(order, orderProducts);
    }
}
