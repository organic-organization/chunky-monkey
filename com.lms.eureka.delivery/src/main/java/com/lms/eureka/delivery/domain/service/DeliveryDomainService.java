package com.lms.eureka.delivery.domain.service;

import com.lms.eureka.delivery.application.dto.DeliveryDto;
import com.lms.eureka.delivery.domain.entity.delivery.Delivery;
import com.lms.eureka.delivery.domain.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryDomainService {
    private final DeliveryRepository deliveryRepository;

    public DeliveryDto createDelivery(DeliveryDto dto) {
        Delivery delivery = deliveryRepository.save(dto.toEntity());
        return DeliveryDto.from(delivery);
    }
}
