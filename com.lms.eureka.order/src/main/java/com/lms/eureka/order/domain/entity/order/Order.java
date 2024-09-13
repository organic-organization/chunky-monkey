package com.lms.eureka.order.domain.entity.order;

import com.lms.eureka.order.domain.entity.BaseEntity;
import com.lms.eureka.order.domain.entity.OrderProduct.OrderProduct;
import com.lms.eureka.order.domain.entity.type.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "p_order")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orderId;

    @Column(name = "request_company_id", nullable = false)
    private UUID requestCompanyId;

    @Column(name = "recipient_company_id", nullable = false)
    private UUID recipientCompanyId;

    @Setter
    @Column(name = "order_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "delivery_destination", nullable = false)
    private String deliveryDestination;

    @Setter
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @OneToMany(mappedBy = "order")
    private List<OrderProduct> orderProductList = new ArrayList<>();

    public static Order create(UUID requestCompanyId, UUID recipientCompanyId, OrderStatus orderStatus, String deliveryDestination, String createdBy) {
        Order order = Order.builder()
                .requestCompanyId(requestCompanyId)
                .recipientCompanyId(recipientCompanyId)
                .orderStatus(orderStatus)
                .deliveryDestination(deliveryDestination)
                .isDeleted(false)
                .build();
        order.setCreatedBy(createdBy);
        return order;
    }
}
