package com.lms.eureka.order.presentation.request;

import com.lms.eureka.order.domain.entity.type.OrderStatus;

import java.util.List;

public record UpdateOrderRequestDto(
        OrderStatus orderStatus,
        List<UpdateOrderProductRequestDto> updateOrderProductRequests
) {
}
