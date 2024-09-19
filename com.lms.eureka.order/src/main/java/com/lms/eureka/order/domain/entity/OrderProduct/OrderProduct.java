package com.lms.eureka.order.domain.entity.OrderProduct;

import com.lms.eureka.order.domain.entity.BaseEntity;
import com.lms.eureka.order.domain.entity.order.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "p_order_product")
public class OrderProduct extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_product_id", nullable = false, updatable = false)
    private UUID orderProductId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Setter
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Setter
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    public static OrderProduct create(Order order, UUID productId, Integer quantity) {
        OrderProduct orderProduct = OrderProduct.builder()
                .order(order)
                .productId(productId)
                .quantity(quantity)
                .isDeleted(false)
                .build();
        orderProduct.setCreatedBy(order.getCreatedBy());

        return orderProduct;
    }

    public void updateQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
