package com.lms.eureka.hub.application.dto.hubRoute;

import com.lms.eureka.hub.domain.entity.hubRoute.HubRoute;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HubRouteMapper {
    @Mapping(target = "departureHubId", source = "departureHub.id")
    @Mapping(target = "arrivalHubId", source = "arrivalHub.id")
    HubRouteDto toDto(HubRoute hubRoute);
}
