package com.lms.eureka.delivery.application.dto;

import com.lms.eureka.delivery.domain.entity.delivery.Delivery;
import com.lms.eureka.delivery.domain.entity.type.OrderStatus;
import com.lms.eureka.delivery.presentation.request.CreateDeliveryRequestDto;
import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Builder(access = AccessLevel.PRIVATE)
public record DeliveryDto(
        UUID deliveryId,
        UUID orderId,
        OrderStatus orderStatus,
        UUID startHubId,
        UUID endHubId,
        String deliveryDestination,
        String recipientSlackId,
        Boolean isDeleted,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime updatedAt,
        String updatedBy,
        LocalDateTime deletedAt,
        String deletedBy
) {

    public static DeliveryDto create(CreateDeliveryRequestDto request, Map<UUID, UUID> companyHubMap) {
        return DeliveryDto.builder()
                .orderId(request.orderId())
                .orderStatus(request.orderStatus())
                .startHubId(companyHubMap.get(request.requestCompanyId()))
                .endHubId(companyHubMap.get(request.recipientCompanyId()))
                .deliveryDestination(request.deliveryDestination())
                .recipientSlackId(request.recipientSlackId())
                .build();
    }

    public static DeliveryDto from(Delivery delivery) {
        return DeliveryDto.builder()
                .deliveryId(delivery.getDeliveryId())
                .orderId(delivery.getOrderId())
                .orderStatus(delivery.getOrderStatus())
                .startHubId(delivery.getStartHubId())
                .endHubId(delivery.getEndHubId())
                .deliveryDestination(delivery.getDeliveryDestination())
                .recipientSlackId(delivery.getRecipientSlackId())
                .isDeleted(delivery.getIsDeleted())
                .createdAt(delivery.getCreatedAt())
                .createdBy(delivery.getCreatedBy())
                .updatedAt(delivery.getUpdatedAt())
                .updatedBy(delivery.getUpdatedBy())
                .deletedAt(delivery.getDeletedAt())
                .deletedBy(delivery.getDeletedBy())
                .build();
    }

    public Delivery toEntity() {
        return Delivery.create(
                orderId,
                orderStatus,
                deliveryDestination,
                startHubId,
                endHubId,
                recipientSlackId
        );
    }
}
