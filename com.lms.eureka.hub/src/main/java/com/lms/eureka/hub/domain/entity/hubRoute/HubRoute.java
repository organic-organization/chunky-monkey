package com.lms.eureka.hub.domain.entity.hubRoute;

import com.lms.eureka.hub.domain.entity.BaseEntity;
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
@Table(name = "p_hub_routes")
public class HubRoute extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private UUID departureHubId;

    @NotNull
    private UUID arrivalHubId;

    @NotNull
    private long duration;

    @NotNull
    private Boolean isDelete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private HubRoute parent;

    public static HubRoute create(UUID departureHubId, UUID arrivalHubId, long duration, String username) {
        HubRoute hubRoute = HubRoute.builder()
                .departureHubId(departureHubId)
                .arrivalHubId(arrivalHubId)
                .duration(duration)
                .isDelete(false)
                .build();
        hubRoute.setCreatedBy(username);
        return hubRoute;
    }

    public void delete(String username) {
        this.setDeletedBy(username);
        this.isDelete = true;
    }

}