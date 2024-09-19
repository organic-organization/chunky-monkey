package com.lms.eureka.hub.application.dto.deliveryAgent;

import com.lms.eureka.hub.domain.entity.deliveryAgent.DeliveryAgent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DeliveryAgentMapper {
    @Mapping(target = "hubId", source = "hub.id")
    DeliveryAgentDto toDto(DeliveryAgent deliveryAgent);
}
