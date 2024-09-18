package com.lms.eureka.order.presentation.reponse;

import com.lms.eureka.order.application.dto.OrderProductDto;
import com.lms.eureka.order.domain.entity.OrderProduct.OrderProduct;
import lombok.Builder;

import java.util.UUID;

@Builder
public record OrderProductResponseDto(
        UUID orderProductId,
        UUID productId,
        Integer quantity
) {
    public static OrderProductResponseDto from(OrderProductDto dto) {
        return OrderProductResponseDto.builder()
                .orderProductId(dto.orderProductId())
                .productId(dto.productId())
                .quantity(dto.quantity())
                .build();
    }
}
