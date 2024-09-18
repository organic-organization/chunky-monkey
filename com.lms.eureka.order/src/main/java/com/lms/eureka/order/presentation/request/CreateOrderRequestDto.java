package com.lms.eureka.order.presentation.request;

import com.lms.eureka.order.application.dto.OrderDto;

import java.util.List;
import java.util.UUID;

public record CreateOrderRequestDto(
        UUID requestCompanyId,
        UUID recipientCompanyId,
        String deliveryDestination,
        List<CreateOrderProductRequestDto> products
) {
    public OrderDto toDto(){
        return OrderDto.of(requestCompanyId, recipientCompanyId, deliveryDestination);
    }
}
