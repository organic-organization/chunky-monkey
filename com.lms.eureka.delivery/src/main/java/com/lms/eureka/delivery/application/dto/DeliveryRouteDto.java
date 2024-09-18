package com.lms.eureka.delivery.application.dto;

import com.lms.eureka.delivery.domain.entity.deliveryRoute.DeliveryRoute;
import com.lms.eureka.delivery.domain.entity.type.OrderStatus;
import lombok.Builder;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record DeliveryRouteDto(
        UUID deliveryRouteId,
        UUID deliveryId,
        int sequence,
        OrderStatus orderStatus,
        UUID startHubId,
        UUID endHubId,
        long expectedDistance,
        Duration expectedDuration,
        long actualDistance,
        Duration actualDuration,
        Boolean isDeleted,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime updatedAt,
        String updatedBy,
        LocalDateTime deletedAt,
        String deletedBy
) {
    public static DeliveryRouteDto create(DeliveryDto deliveryDto, HubRouteDto hubRouteDto) {
        return DeliveryRouteDto.builder()
                .deliveryId(deliveryDto.deliveryId())
                .orderStatus(deliveryDto.orderStatus())
                .startHubId(deliveryDto.startHubId())
                .endHubId(deliveryDto.endHubId())
                .expectedDistance(hubRouteDto.distance())
                .expectedDuration(hubRouteDto.transitTime())
                .build();
    }


    public static DeliveryRouteDto from(DeliveryRoute deliveryRoute) {
        return DeliveryRouteDto.builder()
                .deliveryId(deliveryRoute.getDeliveryId())
                .sequence(deliveryRoute.getSequence())
                .orderStatus(deliveryRoute.getOrderStatus())
                .startHubId(deliveryRoute.getStartHubId())
                .endHubId(deliveryRoute.getEndHubId())
                .expectedDistance(deliveryRoute.getExpectedDistance())
                .expectedDuration(deliveryRoute.getExpectedDuration())
                .actualDistance(deliveryRoute.getActualDistance())
                .actualDuration(deliveryRoute.getActualDuration())
                .isDeleted(deliveryRoute.getIsDeleted())
                .createdAt(deliveryRoute.getCreatedAt())
                .createdBy(deliveryRoute.getCreatedBy())
                .updatedAt(deliveryRoute.getUpdatedAt())
                .updatedBy(deliveryRoute.getUpdatedBy())
                .deletedAt(deliveryRoute.getDeletedAt())
                .deletedBy(deliveryRoute.getDeletedBy())
                .build();
    }

    public DeliveryRoute toEntity() {
        return DeliveryRoute.create(deliveryId, orderStatus, startHubId, endHubId, expectedDistance, expectedDuration);
    }
}
