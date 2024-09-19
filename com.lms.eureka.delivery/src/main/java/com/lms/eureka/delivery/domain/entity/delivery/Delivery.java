package com.lms.eureka.delivery.domain.entity.delivery;

import com.lms.eureka.delivery.domain.entity.BaseEntity;
import com.lms.eureka.delivery.domain.entity.type.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "p_delivery")
public class Delivery extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID deliveryId;

    @Column(name = "order_id", nullable = false)
    private UUID orderId;

    @Setter
    @Column(name = "order_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "start_hub_id", nullable = false)
    private UUID startHubId;

    @Column(name = "end_hub_id", nullable = false)
    private UUID endHubId;

    @Column(name = "delivery_destination", nullable = false)
    private String deliveryDestination;

    @Column(name = "recipient_company_id", nullable = false)
    private UUID recipientCompanyId;

    @Column(name = "recipient_slack_id", nullable = false)
    private String recipientSlackId;

    @Setter
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    public static Delivery create(UUID orderId, OrderStatus orderStatus, String deliveryDestination, UUID startHubId, UUID endHubId, String recipientSlackId) {
        return Delivery.builder()
                .orderId(orderId)
                .orderStatus(orderStatus)
                .deliveryDestination(deliveryDestination)
                .startHubId(startHubId)
                .endHubId(endHubId)
                .recipientSlackId(recipientSlackId)
                .isDeleted(false)
                .build();
    }

    public void updateStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
