package com.lms.eureka.order.application.client;

import com.lms.eureka.order.presentation.reponse.CommonResponse;
import com.lms.eureka.order.presentation.request.CreateDeliveryRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "delivery-service")
public interface DeliveryClient {
    @PostMapping("api/delivery/create")
    CommonResponse<Void> createDelivery(@RequestBody CreateDeliveryRequestDto dto);
}
