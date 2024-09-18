package com.lms.eureka.delivery.domain.service;

import com.lms.eureka.delivery.application.dto.DeliveryRouteDto;
import com.lms.eureka.delivery.domain.repository.DeliveryRouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryRouteDomainService {
    private final DeliveryRouteRepository deliveryRouteRepository;

    public void createDeliveryRoute(DeliveryRouteDto dto) {
        deliveryRouteRepository.save(dto.toEntity());
    }
}
