package com.lms.eureka.delivery.domain.service;

import com.lms.eureka.delivery.application.dto.DeliveryRouteDto;
import com.lms.eureka.delivery.domain.entity.deliveryRoute.DeliveryRoute;
import com.lms.eureka.delivery.domain.entity.type.OrderStatus;
import com.lms.eureka.delivery.domain.exception.DeliveryException;
import com.lms.eureka.delivery.domain.exception.DeliveryExceptionCase;
import com.lms.eureka.delivery.domain.repository.deliveryRoute.DeliveryRouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryRouteDomainService {
    private final DeliveryRouteRepository deliveryRouteRepository;

    public void createDeliveryRoute(DeliveryRouteDto dto) {
        deliveryRouteRepository.save(dto.toEntity());
    }

    public void updateStatus(UUID deliveryRouteId, OrderStatus orderStatus) {
        DeliveryRoute deliveryRoute = deliveryRouteRepository.findById(deliveryRouteId).orElseThrow(() -> new DeliveryException(DeliveryExceptionCase.DELIVERY_NOT_FOUND));
        deliveryRoute.updateStatus(orderStatus);
    }

    public UUID getDeliveryRoutesByDeliveryManager(UUID startHubId) {
        return deliveryRouteRepository.findDeliveryManagerWhereMinCnt(startHubId);
    }

    public Page<DeliveryRouteDto> getDeliveries(UUID deliveryManagerId, Pageable pageable) {

        return deliveryRouteRepository.findAllByDeliveryManagerId(deliveryManagerId, pageable).map(DeliveryRouteDto::from);
    }
}
