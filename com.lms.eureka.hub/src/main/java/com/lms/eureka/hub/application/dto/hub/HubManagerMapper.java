package com.lms.eureka.hub.application.dto.hub;

import com.lms.eureka.hub.domain.entity.hub.HubManager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HubManagerMapper {
    @Mapping(target = "hubId", source = "hub.id")
    HubManagerDto toDto(HubManager hubManager);
}
