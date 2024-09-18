package com.lms.eureka.order.application.dto;

import com.lms.eureka.order.domain.entity.OrderProduct.OrderProduct;
import lombok.AccessLevel;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder(access = AccessLevel.PRIVATE)
public record OrderProductDto(
        UUID orderProductId,
        OrderDto orderDto,
        UUID productId,
        Integer quantity,
        Boolean isDeleted,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime updatedAt,
        String updatedBy,
        LocalDateTime deletedAt,
        String deletedBy
) {
    public static OrderProductDto of(UUID productId, Integer quantity) {
        return OrderProductDto.builder()
                .productId(productId)
                .quantity(quantity)
                .build();
    }

    public OrderProduct toEntity(String username) {
        return OrderProduct.create(orderDto.toEntity(username), productId, quantity);
    }

    public static OrderProductDto from(OrderProduct orderProduct) {
        return OrderProductDto.builder()
                .orderProductId(orderProduct.getOrderProductId())
                .orderDto(OrderDto.from(orderProduct.getOrder()))
                .productId(orderProduct.getProductId())
                .quantity(orderProduct.getQuantity())
                .isDeleted(orderProduct.getIsDeleted())
                .createdBy(orderProduct.getCreatedBy())
                .createdAt(orderProduct.getCreatedAt())
                .updatedAt(orderProduct.getUpdatedAt())
                .updatedBy(orderProduct.getUpdatedBy())
                .deletedAt(orderProduct.getDeletedAt())
                .deletedBy(orderProduct.getDeletedBy())
                .build();
    }
}
