package com.lms.eureka.order.presentation.reponse;

import com.lms.eureka.order.application.dto.OrderDto;
import com.lms.eureka.order.application.dto.OrderProductDto;
import com.lms.eureka.order.domain.entity.type.OrderStatus;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record OrderDetailResponseDto(
        UUID orderId,
        UUID requestCompanyId,
        UUID recipientCompanyId,
        String orderStatus,
        String deliveryDestination,
        LocalDateTime createdAt,
        List<OrderProductResponseDto> orderProducts
) {

    public static OrderDetailResponseDto from(OrderDto orderDto, List<OrderProductDto> orderProductsDto) {
        return OrderDetailResponseDto.builder()
                .orderId(orderDto.orderId())
                .requestCompanyId(orderDto.requestCompanyId())
                .recipientCompanyId(orderDto.recipientCompanyId())
                .orderStatus(orderDto.orderStatus().getName())
                .deliveryDestination(orderDto.deliveryDestination())
                .createdAt(orderDto.createdAt())
                .orderProducts(orderProductsDto.stream().map(OrderProductResponseDto::from).toList())
                .build();
    }
}
