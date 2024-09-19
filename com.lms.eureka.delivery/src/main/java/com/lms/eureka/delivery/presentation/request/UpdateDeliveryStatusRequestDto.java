package com.lms.eureka.delivery.presentation.request;

import com.lms.eureka.delivery.domain.entity.type.OrderStatus;

import java.util.UUID;

public record UpdateDeliveryStatusRequestDto(
        UUID deliveryRouteId,
        OrderStatus orderStatus,
        OrderStatus deliveryRouteStatus
) {
}
