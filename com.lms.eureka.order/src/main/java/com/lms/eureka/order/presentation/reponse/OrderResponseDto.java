package com.lms.eureka.order.presentation.reponse;

import com.lms.eureka.order.application.dto.OrderDto;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record OrderResponseDto(
        UUID orderId,
        UUID requestCompanyId,
        UUID recipientCompanyId,
        String orderStatus,
        String deliveryDestination,
        LocalDateTime creatOrderDate
) {
    public static OrderResponseDto from(OrderDto dto) {
        return OrderResponseDto.builder()
                .orderId(dto.orderId())
                .requestCompanyId(dto.requestCompanyId())
                .recipientCompanyId(dto.recipientCompanyId())
                .orderStatus(dto.orderStatus().getName())
                .deliveryDestination(dto.deliveryDestination())
                .creatOrderDate(dto.createdAt())
                .build();
    }
}
