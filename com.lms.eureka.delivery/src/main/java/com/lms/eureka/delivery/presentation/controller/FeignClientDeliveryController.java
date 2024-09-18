package com.lms.eureka.delivery.presentation.controller;

import com.lms.eureka.delivery.application.service.DeliveryService;
import com.lms.eureka.delivery.presentation.reponse.CommonResponse;
import com.lms.eureka.delivery.presentation.request.CreateDeliveryRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/api/delivery")
public class FeignClientDeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping("/create")
    public CommonResponse<Void> createDelivery(@RequestBody CreateDeliveryRequestDto request){
        deliveryService.createDelivery(request);
        return CommonResponse.success(null);
    }
}
