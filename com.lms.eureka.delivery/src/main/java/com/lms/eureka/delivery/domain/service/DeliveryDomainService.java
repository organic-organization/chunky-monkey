package com.lms.eureka.delivery.domain.service;

import com.lms.eureka.delivery.application.dto.DeliveryDto;
import com.lms.eureka.delivery.application.dto.DeliveryRouteDto;
import com.lms.eureka.delivery.domain.entity.delivery.Delivery;
import com.lms.eureka.delivery.domain.entity.type.OrderStatus;
import com.lms.eureka.delivery.domain.exception.DeliveryException;
import com.lms.eureka.delivery.domain.exception.DeliveryExceptionCase;
import com.lms.eureka.delivery.domain.repository.delivery.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryDomainService {
    private final DeliveryRepository deliveryRepository;

    public DeliveryDto createDelivery(DeliveryDto dto) {
        Delivery delivery = deliveryRepository.save(dto.toEntity());
        return DeliveryDto.from(delivery);
    }

    public void updateStatus(UUID deliveryId, OrderStatus orderStatus) {
        Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow(() -> new DeliveryException(DeliveryExceptionCase.DELIVERY_NOT_FOUND));
        delivery.updateStatus(orderStatus);
    }

    public Page<DeliveryDto> getDeliveriesByMaster(Pageable pageable) {

        return deliveryRepository.findAll(pageable).map(DeliveryDto::from);
    }

    public Page<DeliveryDto> getDeliveriesByStartHubId(UUID startHubId, Pageable pageable) {
        return deliveryRepository.findAllByStartHubId(startHubId, pageable).map(DeliveryDto::from);
    }
}
