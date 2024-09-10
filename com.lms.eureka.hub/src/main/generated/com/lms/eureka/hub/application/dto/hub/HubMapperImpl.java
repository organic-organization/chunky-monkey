package com.lms.eureka.hub.application.dto.hub;

import com.lms.eureka.hub.domain.entity.hub.Hub;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-10T13:36:59+0900",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.jar, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class HubMapperImpl implements HubMapper {

    @Override
    public HubDto toDto(Hub hub) {
        if ( hub == null ) {
            return null;
        }

        UUID id = null;
        String name = null;
        String address = null;
        double latitude = 0.0d;
        double longitude = 0.0d;
        long routeIndex = 0L;
        LocalDateTime createdAt = null;
        String createdBy = null;
        LocalDateTime updatedAt = null;
        String updatedBy = null;
        LocalDateTime deletedAt = null;
        String deletedBy = null;

        id = hub.getId();
        name = hub.getName();
        address = hub.getAddress();
        latitude = hub.getLatitude();
        longitude = hub.getLongitude();
        routeIndex = hub.getRouteIndex();
        createdAt = hub.getCreatedAt();
        createdBy = hub.getCreatedBy();
        updatedAt = hub.getUpdatedAt();
        updatedBy = hub.getUpdatedBy();
        deletedAt = hub.getDeletedAt();
        deletedBy = hub.getDeletedBy();

        HubDto hubDto = new HubDto( id, name, address, latitude, longitude, routeIndex, createdAt, createdBy, updatedAt, updatedBy, deletedAt, deletedBy );

        return hubDto;
    }
}
