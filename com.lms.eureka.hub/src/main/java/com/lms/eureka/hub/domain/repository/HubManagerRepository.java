package com.lms.eureka.hub.domain.repository;

import com.lms.eureka.hub.domain.entity.hubManager.HubManager;

public interface HubManagerRepository {
    HubManager save(HubManager hubManager);
}
