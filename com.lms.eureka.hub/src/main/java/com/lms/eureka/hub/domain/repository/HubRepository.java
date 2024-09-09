package com.lms.eureka.hub.domain.repository;

import com.lms.eureka.hub.domain.model.Hub;
import java.util.Optional;
import java.util.UUID;

public interface HubRepository {
    Hub save(Hub hub);
    Optional<Hub> findById(UUID hubId);
}
