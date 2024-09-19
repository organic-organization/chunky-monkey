package com.lms.eureka.hub.domain.entity.hubManager;

import com.lms.eureka.hub.domain.entity.BaseEntity;
import com.lms.eureka.hub.domain.entity.hub.Hub;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "p_hub_managers")
public class HubManager extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hub_id")
    private Hub hub;

    @NotNull
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    private Boolean isDelete;

    public static HubManager create(Hub hub, Long userId, String username) {
        HubManager hubManager = HubManager.builder()
                .hub(hub)
                .userId(userId)
                .isDelete(false)
                .build();
        hubManager.setCreatedBy(username);
        return hubManager;
    }

    public void delete(String username) {
        this.setDeletedBy(username);
        this.isDelete = true;
    }

}
