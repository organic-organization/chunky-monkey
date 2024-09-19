package com.lms.eureka.hub.infra.repository.deliveryAgent;

import com.lms.eureka.hub.domain.entity.deliveryAgent.DeliveryAgent;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryAgentJpaRepository extends JpaRepository<DeliveryAgent, UUID> {
}
