package com.lms.eureka.delivery.domain.repository.delivery;

import com.lms.eureka.delivery.domain.entity.delivery.Delivery;
import com.lms.eureka.delivery.infra.repository.DeliveryJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface DeliveryRepository extends DeliveryJpaRepository {
    Page<Delivery> findAllByStartHubId(UUID startHubId, Pageable pageable);
    Page<Delivery> findAllByRecipientCompanyId(UUID recipientCompanyId, Pageable pageable);
}
