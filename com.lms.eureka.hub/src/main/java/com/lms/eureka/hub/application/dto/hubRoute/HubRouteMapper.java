package com.lms.eureka.hub.application.dto.hubRoute;

import com.lms.eureka.hub.domain.entity.hubRoute.HubRoute;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HubRouteMapper {
    HubRouteDto toDto(HubRoute hubRoute);
}
