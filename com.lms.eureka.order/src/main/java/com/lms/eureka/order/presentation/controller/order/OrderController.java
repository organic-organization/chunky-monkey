package com.lms.eureka.order.presentation.controller.order;

import com.lms.eureka.order.application.dto.OrderProductDto;
import com.lms.eureka.order.application.service.OrderService;
import com.lms.eureka.order.domain.success.OrderSuccessCase;
import com.lms.eureka.order.presentation.reponse.CommonResponse;
import com.lms.eureka.order.presentation.request.CreateOrderProductRequestDto;
import com.lms.eureka.order.presentation.request.CreateOrderRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/company-manager/order")
    public CommonResponse<String> createOrder(
            @RequestBody CreateOrderRequestDto request,
            @RequestHeader(value = "username", required = true) String username
    ){

        orderService.createOrder(
                username, request.toDto(),
                request.products().stream().map(CreateOrderProductRequestDto::toDto).toList()
        );

        return CommonResponse.success(OrderSuccessCase.ORDER_CREATE.getMessage());
    }
}
