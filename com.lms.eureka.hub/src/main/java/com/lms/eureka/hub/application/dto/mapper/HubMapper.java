package com.lms.eureka.hub.application.dto.mapper;

import com.lms.eureka.hub.application.dto.HubDto;
import com.lms.eureka.hub.domain.model.Hub;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HubMapper {
    HubDto toResponse(Hub hub);
}
