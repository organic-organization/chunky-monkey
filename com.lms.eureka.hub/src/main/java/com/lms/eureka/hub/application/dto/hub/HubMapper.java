package com.lms.eureka.hub.application.dto.hub;

import com.lms.eureka.hub.domain.entity.hub.Hub;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HubMapper {
    HubDto toDto(Hub hub);
}
