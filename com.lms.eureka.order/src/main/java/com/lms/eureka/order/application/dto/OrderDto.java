package com.lms.eureka.order.application.dto;

import com.lms.eureka.order.domain.entity.order.Order;
import com.lms.eureka.order.domain.entity.type.OrderStatus;
import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder(access = AccessLevel.PRIVATE)
public record OrderDto(
        UUID orderId,
        UUID requestCompanyId,
        UUID recipientCompanyId,
        OrderStatus orderStatus,
        String deliveryDestination,
        Boolean isDeleted,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime updatedAt,
        String updatedBy,
        LocalDateTime deletedAt,
        String deletedBy
) {
    public static OrderDto of(UUID requestCompanyId, UUID recipientCompanyId, String deliveryDestination) {
        return OrderDto.builder()
                .requestCompanyId(requestCompanyId)
                .recipientCompanyId(recipientCompanyId)
                .deliveryDestination(deliveryDestination)
                .orderStatus(OrderStatus.ORDER_IN_PROGRESS)
                .build();
    }

    public Order toEntity(String createdBy) {
        return Order.create(requestCompanyId, recipientCompanyId, orderStatus, deliveryDestination, createdBy);
    }

    public static OrderDto from(Order order) {
        return OrderDto.builder()
                .orderId(order.getOrderId())
                .requestCompanyId(order.getRequestCompanyId())
                .recipientCompanyId(order.getRecipientCompanyId())
                .orderStatus(order.getOrderStatus())
                .deliveryDestination(order.getDeliveryDestination())
                .isDeleted(order.getIsDeleted())
                .createdAt(order.getCreatedAt())
                .createdBy(order.getCreatedBy())
                .updatedAt(order.getUpdatedAt())
                .updatedBy(order.getUpdatedBy())
                .deletedAt(order.getDeletedAt())
                .deletedBy(order.getDeletedBy())
                .build();
    }
}
