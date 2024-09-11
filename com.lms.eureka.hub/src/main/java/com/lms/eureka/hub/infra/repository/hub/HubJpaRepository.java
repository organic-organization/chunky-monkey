package com.lms.eureka.hub.infra.repository.hub;

import com.lms.eureka.hub.domain.entity.hub.Hub;
import com.lms.eureka.hub.domain.repository.HubRepository;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HubJpaRepository extends JpaRepository<Hub, UUID>, HubQuerydslRepository, HubRepository {
}
