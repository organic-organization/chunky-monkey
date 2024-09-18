package com.lms.eureka.order.presentation.request;

import com.lms.eureka.order.application.dto.OrderDto;
import com.lms.eureka.order.domain.entity.type.OrderStatus;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CreateDeliveryRequestDto(
        UUID orderId,
        OrderStatus orderStatus,
        String deliveryDestination,
        UUID recipientCompanyId,
        String recipientSlackId
) {
    public static CreateDeliveryRequestDto from(OrderDto saveOrderDto, String recipientSlackId) {
        return CreateDeliveryRequestDto.builder()
                .orderId(saveOrderDto.orderId())
                .orderStatus(saveOrderDto.orderStatus())
                .deliveryDestination(saveOrderDto.deliveryDestination())
                .recipientCompanyId(saveOrderDto.recipientCompanyId())
                .recipientSlackId(recipientSlackId)
                .build();
    }
}
