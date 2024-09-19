package com.lms.eureka.delivery.presentation.reponse;

import com.lms.eureka.delivery.application.dto.DeliveryDto;
import com.lms.eureka.delivery.domain.entity.type.OrderStatus;
import lombok.Builder;

import java.util.UUID;

@Builder
public record DeliveryResponseDto(
        UUID deliveryId,
        UUID orderId,
        OrderStatus orderStatus,
        UUID startHubId,
        UUID endHubId,
        String deliveryDestination,
        String recipientSlackId
) {
    public static DeliveryResponseDto from(DeliveryDto dto) {
        return DeliveryResponseDto.builder()
                .deliveryId(dto.deliveryId())
                .orderId(dto.orderId())
                .orderStatus(dto.orderStatus())
                .startHubId(dto.startHubId())
                .endHubId(dto.endHubId())
                .deliveryDestination(dto.deliveryDestination())
                .recipientSlackId(dto.recipientSlackId())
                .build();
    }
}
