package com.lms.eureka.order.domain.service;

import com.lms.eureka.order.application.dto.OrderDto;
import com.lms.eureka.order.application.dto.OrderProductDto;
import com.lms.eureka.order.domain.entity.OrderProduct.OrderProduct;
import com.lms.eureka.order.domain.exception.OrderException;
import com.lms.eureka.order.domain.exception.OrderExceptionCase;
import com.lms.eureka.order.domain.repository.OrderProductRepository;
import com.lms.eureka.order.presentation.request.UpdateOrderProductRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderProductDomainService {
    private final OrderProductRepository orderProductRepository;

    public void createOrderProduct(String username, OrderDto saveOrderDto, List<OrderProductDto> list) {
        orderProductRepository.saveAll(list.stream().map(dto -> dto.toEntity(username)).toList());
    }

    public List<OrderProductDto> getProductListByOrderId(UUID orderId) {
        return orderProductRepository.findAllByOrder_OrderId(orderId).stream().map(OrderProductDto::from).toList();
    }

    public List<OrderProductDto> updateProductByOrderProductId(UUID orderId, List<UpdateOrderProductRequestDto> updateOrderProductRequests) {
        for(UpdateOrderProductRequestDto updateOrderProductRequest : updateOrderProductRequests) {
            OrderProduct orderProduct = orderProductRepository.findById(updateOrderProductRequest.orderProductId()).orElseThrow(() -> new OrderException(OrderExceptionCase.PRODUCT_NOT_FOUND));
            orderProduct.updateQuantity(updateOrderProductRequest.quantity());
        }

        return orderProductRepository.findAllByOrder_OrderId(orderId).stream().map(OrderProductDto::from).toList();
    }
}
