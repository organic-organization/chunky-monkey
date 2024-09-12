package com.lms.eureka.hub.domain.entity.hubRoute;

import com.lms.eureka.hub.domain.entity.BaseEntity;
import com.lms.eureka.hub.domain.entity.hub.Hub;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.Duration;
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
    private String displayName;

    @ManyToOne
    @JoinColumn(name = "departure_hub_id", nullable = false)
    private Hub departureHub;

    @ManyToOne
    @JoinColumn(name = "arrival_hub_id", nullable = false)
    private Hub arrivalHub;

    @NotNull
    private Duration transitTime;

    @NotNull
    private long distance;

    @NotNull
    private Boolean isDelete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private HubRoute parent;

    public static HubRoute create(Hub departureHub, Hub arrivalHub, String displayName, long distance, Duration transitTime, String username) {
        HubRoute hubRoute = HubRoute.builder()
                .departureHub(departureHub)
                .arrivalHub(arrivalHub)
                .displayName(displayName)
                .distance(distance)
                .transitTime(transitTime)
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