package com.lms.eureka.delivery.presentation.reponse;

import com.lms.eureka.delivery.application.dto.DeliveryRouteDto;
import com.lms.eureka.delivery.domain.entity.type.OrderStatus;
import lombok.Builder;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record DeliveryRouteResponseDto(
        UUID deliveryRouteId,
        UUID deliveryId,
        UUID deliveryManagerId,
        OrderStatus orderStatus,
        UUID startHubId,
        UUID endHubId,
        long expectedDistance,
        Duration expectedDuration,
        long actualDistance,
        Duration actualDuration,
        LocalDateTime createdAt
) {
    public static DeliveryRouteResponseDto from(DeliveryRouteDto dto) {
        return DeliveryRouteResponseDto.builder()
                .deliveryRouteId(dto.deliveryRouteId())
                .deliveryId(dto.deliveryId())
                .deliveryManagerId(dto.deliveryManagerId())
                .orderStatus(dto.orderStatus())
                .startHubId(dto.startHubId())
                .endHubId(dto.endHubId())
                .expectedDistance(dto.expectedDistance())
                .expectedDuration(dto.expectedDuration())
                .actualDistance(dto.actualDistance())
                .actualDuration(dto.actualDuration())
                .createdAt(dto.createdAt())
                .build();
    }
}
