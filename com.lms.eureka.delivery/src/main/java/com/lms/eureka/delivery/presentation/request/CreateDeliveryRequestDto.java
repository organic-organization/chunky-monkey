package com.lms.eureka.delivery.presentation.request;

import com.lms.eureka.delivery.domain.entity.type.OrderStatus;

import java.util.UUID;

public record CreateDeliveryRequestDto(
        UUID orderId,
        OrderStatus orderStatus,
        String deliveryDestination,
        UUID requestCompanyId,
        UUID recipientCompanyId,
        String recipientSlackId
) {
}
