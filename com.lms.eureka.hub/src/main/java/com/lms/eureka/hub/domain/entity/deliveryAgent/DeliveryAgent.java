package com.lms.eureka.hub.domain.entity.deliveryAgent;

import com.lms.eureka.hub.domain.entity.BaseEntity;
import com.lms.eureka.hub.domain.entity.hub.Hub;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "p_deliveryAgent")
public class DeliveryAgent extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hub_id")
    private Hub hub;

    @NotNull
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DeliveryAgentType type;

    @NotNull
    @Column(name = "slack_id")
    private String slackId;

    @NotNull
    private Boolean isDelete;

    public static DeliveryAgent create(Hub hub, Long userId, DeliveryAgentType type, String slackId, String username) {
        DeliveryAgent deliveryAgent = DeliveryAgent.builder()
                .hub(hub)
                .userId(userId)
                .type(type)
                .slackId(slackId)
                .isDelete(false)
                .build();
        deliveryAgent.setCreatedBy(username);
        return deliveryAgent;
    }

    public void delete(String username) {
        this.setDeletedBy(username);
        this.isDelete = true;
    }

}