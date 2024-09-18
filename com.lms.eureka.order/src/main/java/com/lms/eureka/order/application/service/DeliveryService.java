package com.lms.eureka.order.application.service;

import com.lms.eureka.order.application.client.DeliveryClient;
import com.lms.eureka.order.domain.exception.OrderException;
import com.lms.eureka.order.domain.exception.OrderExceptionCase;
import com.lms.eureka.order.presentation.request.CreateDeliveryRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final DeliveryClient deliveryClient;

    public void createDelivery(CreateDeliveryRequestDto dto) {
        if (!deliveryClient.createDelivery(dto).getStatus().equals(HttpStatus.OK)) {
            throw new OrderException(OrderExceptionCase.USER_NOT_FOUND);
        }
    }
}
