package com.lms.eureka.order.presentation.request;

import com.lms.eureka.order.application.dto.OrderProductDto;
import com.lms.eureka.order.domain.entity.OrderProduct.OrderProduct;

import java.util.UUID;

public record CreateOrderProductRequestDto(
        UUID productId,
        Integer quantity
) {
    public OrderProductDto toDto(){
        return OrderProductDto.of(productId, quantity);
    }
}
