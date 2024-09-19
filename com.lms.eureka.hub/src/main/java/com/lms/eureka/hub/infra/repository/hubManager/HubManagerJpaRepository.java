package com.lms.eureka.hub.infra.repository.hubManager;

import com.lms.eureka.hub.domain.entity.hub.HubManager;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HubManagerJpaRepository extends JpaRepository<HubManager, UUID> {
}
