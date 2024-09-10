package com.lms.eureka.hub.domain.entity.hub;

import com.lms.eureka.hub.domain.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "p_hubs")
public class Hub extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    private double latitude;

    @NotNull
    private double longitude;

    @NotNull
    private long routeIndex;

    @NotNull
    private Boolean isDeleted;

    public static Hub create(String name, String address, double latitude, double longitude,
                                long routeIndex, String username) {
        Hub hub = Hub.builder()
                .name(name)
                .address(address)
                .latitude(latitude)
                .longitude(longitude)
                .routeIndex(routeIndex)
                .isDeleted(false)
                .build();
        hub.setCreatedBy(username);
        return hub;
    }

    public void delete(String username) {
        this.setDeletedBy(username);
        this.isDeleted = true;
    }

}
