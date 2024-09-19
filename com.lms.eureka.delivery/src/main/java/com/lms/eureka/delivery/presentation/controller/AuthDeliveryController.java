package com.lms.eureka.delivery.presentation.controller;

import com.lms.eureka.delivery.application.service.DeliveryService;
import com.lms.eureka.delivery.presentation.reponse.CommonResponse;
import com.lms.eureka.delivery.presentation.reponse.DeliveryResponseDto;
import com.lms.eureka.delivery.presentation.reponse.DeliveryRouteResponseDto;
import com.lms.eureka.delivery.presentation.request.UpdateDeliveryStatusRequestDto;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthDeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping("/delivery-agent/delivery/update/{deliveryId}")
    public CommonResponse<String> updateStatus(@PathVariable("deliveryId") UUID deliveryId, @RequestBody UpdateDeliveryStatusRequestDto request){

        deliveryService.updateStatus(deliveryId, request);

        return CommonResponse.success("성공하였습니다.");
    }

    @GetMapping("/master/delivery")
    public CommonResponse<Page<DeliveryResponseDto>> getDeliveriesByMaster(
            @RequestHeader(value = "username", required = true) String username,
            @ParameterObject @PageableDefault(
                    size = 10, sort = {"createAt"}, direction = Sort.Direction.DESC
            ) Pageable pageable
    ){
        return CommonResponse.success(deliveryService.getDeliveriesByMaster(pageable).map(DeliveryResponseDto::from));
    }

    @GetMapping("/hub-manager/delivery")
    public CommonResponse<Page<DeliveryResponseDto>> getDeliveriesByhubManager(
            @RequestHeader(value = "username", required = true) String username,
            @ParameterObject @PageableDefault(
                    size = 10, sort = {"createAt"}, direction = Sort.Direction.DESC
            ) Pageable pageable
    ){
        return CommonResponse.success(deliveryService.getDeliveriesByhubManager(username, pageable).map(DeliveryResponseDto::from));
    }

    @GetMapping("/company-manager/delivery/{deliveryManagerId}")
    public CommonResponse<Page<DeliveryRouteResponseDto>> getDeliveries(
            @PathVariable("deliveryManagerId") UUID deliveryManagerId,
            @ParameterObject @PageableDefault(
                    size = 10, sort = {"createAt"}, direction = Sort.Direction.DESC
            ) Pageable pageable
    ){
        return CommonResponse.success(deliveryService.getDeliveries(deliveryManagerId, pageable).map(DeliveryRouteResponseDto::from));
    }
}
