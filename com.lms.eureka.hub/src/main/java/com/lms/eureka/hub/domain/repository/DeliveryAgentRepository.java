package com.lms.eureka.hub.domain.repository;

import com.lms.eureka.hub.domain.entity.deliveryAgent.DeliveryAgent;
import java.util.UUID;

public interface DeliveryAgentRepository {
    DeliveryAgent save(DeliveryAgent deliveryAgent);
    long countByHubId(UUID hubId);
}
