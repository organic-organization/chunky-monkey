package com.lms.eureka.order.presentation.request;

import com.lms.eureka.order.application.dto.OrderProductDto;

import java.util.UUID;

public record UpdateOrderProductRequestDto(
        UUID orderProductId,
        Integer quantity
) {
}
