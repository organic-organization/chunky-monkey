package com.lms.eureka.hub.application.dto.hubManager;

import com.lms.eureka.hub.domain.entity.hubManager.HubManager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HubManagerMapper {
    @Mapping(target = "hubId", source = "hub.id")
    HubManagerDto toDto(HubManager hubManager);
}
