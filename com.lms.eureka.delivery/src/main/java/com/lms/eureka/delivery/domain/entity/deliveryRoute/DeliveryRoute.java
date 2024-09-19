package com.lms.eureka.delivery.domain.entity.deliveryRoute;

import com.lms.eureka.delivery.domain.entity.BaseEntity;
import com.lms.eureka.delivery.domain.entity.type.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "p_delivery_route")
public class DeliveryRoute extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID deliveryRouteId;

    @Column(name = "delivery_id", nullable = false)
    private UUID deliveryId;

    @Column(name = "sequence")
    private int sequence;

    @Setter
    @Column(name = "order_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "delivery_manager_id", nullable = false)
    private UUID deliveryManagerId;

    @Column(name = "start_hub_id", nullable = false)
    private UUID startHubId;

    @Column(name = "end_hub_id", nullable = false)
    private UUID endHubId;

    @Column(name = "expected_distance", nullable = false)
    private long expectedDistance;

    @Column(name = "expected_duration", nullable = false)
    private Duration expectedDuration;

    @Setter
    @Column(name = "actual_distance")
    private long actualDistance;

    @Setter
    @Column(name = "actual_duration")
    private Duration actualDuration;

    @Setter
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    public static DeliveryRoute create(UUID deliveryId, UUID deliveryManagerId, OrderStatus orderStatus, UUID startHubId, UUID endHubId, long expectedDistance, Duration expectedDuration) {
        return DeliveryRoute.builder()
                .deliveryId(deliveryId)
                .deliveryManagerId(deliveryManagerId)
                .orderStatus(orderStatus)
                .startHubId(startHubId)
                .endHubId(endHubId)
                .expectedDistance(expectedDistance)
                .expectedDuration(expectedDuration)
                .isDeleted(false)
                .build();
    }

    public void updateStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
