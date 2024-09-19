package com.lms.eureka.order.application.service;

import com.lms.eureka.order.application.dto.OrderDto;
import com.lms.eureka.order.application.dto.OrderProductDto;
import com.lms.eureka.order.domain.service.OrderDomainService;
import com.lms.eureka.order.domain.service.OrderProductDomainService;
import com.lms.eureka.order.presentation.reponse.OrderDetailResponseDto;
import com.lms.eureka.order.presentation.reponse.OrderResponseDto;
import com.lms.eureka.order.presentation.request.CreateDeliveryRequestDto;
import com.lms.eureka.order.presentation.request.UpdateOrderRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
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

    @Transactional(readOnly = true)
    public Page<OrderResponseDto> getOrders(String username, Pageable pageable) {
        // company 호출 - 속해있는 company 조회
        String companyId = companyService.getCompany(username);

        Page<OrderDto> orders = orderDomainService.getOrders(companyId, pageable);

        return orders.map(OrderResponseDto::from);
    }

    @Transactional(readOnly = true)
    public OrderDetailResponseDto getOrderByOrderId(UUID orderId) {
        OrderDto order = orderDomainService.getOrderByOrderId(orderId);
        List<OrderProductDto> orderProducts = orderProductDomainService.getProductListByOrderId(order.orderId());

        return OrderDetailResponseDto.from(order, orderProducts);
    }

    public OrderDetailResponseDto updateOrder(String username, UUID orderId, UpdateOrderRequestDto request) {
        OrderDto order = orderDomainService.updateOrder(orderId, request.orderStatus());
        List<OrderProductDto> orderProducts = orderProductDomainService.updateProductByOrderProductId(orderId, request.updateOrderProductRequests());

        return OrderDetailResponseDto.from(order, orderProducts);
    }
}
