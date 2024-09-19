package com.lms.eureka.delivery.infra.repository.deliveryRoute;

import java.util.UUID;

public interface QueryDslDeliveryRouteRepository {

    UUID findDeliveryManagerWhereMinCnt(UUID startHubId);

}