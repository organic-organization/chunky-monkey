package com.lms.eureka.order.presentation.controller.order;

import com.lms.eureka.order.application.service.OrderService;
import com.lms.eureka.order.domain.success.OrderSuccessCase;
import com.lms.eureka.order.presentation.reponse.CommonResponse;
import com.lms.eureka.order.presentation.reponse.OrderDetailResponseDto;
import com.lms.eureka.order.presentation.reponse.OrderResponseDto;
import com.lms.eureka.order.presentation.request.CreateOrderProductRequestDto;
import com.lms.eureka.order.presentation.request.CreateOrderRequestDto;
import com.lms.eureka.order.presentation.request.UpdateOrderRequestDto;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthOrderController {

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

    @GetMapping("/company-manager/order")
    public CommonResponse<Page<OrderResponseDto>> getOrders(
            @RequestHeader(value = "username", required = true) String username,
            @ParameterObject @PageableDefault(
                    size = 10, sort = {"username"}, direction = Sort.Direction.DESC
            ) Pageable pageable
    ){
        return CommonResponse.success(orderService.getOrders(username, pageable));
    }

    @GetMapping("/company-manager/order/{orderId}")
    public CommonResponse<OrderDetailResponseDto> getOrder(@PathVariable("orderId") UUID orderId){
        return CommonResponse.success(orderService.getOrderByOrderId(orderId));
    }

    @PostMapping("/hub-manager/order/{orderId}")
    public CommonResponse<OrderDetailResponseDto> updateOrder(
            @PathVariable UUID orderId,
            @RequestBody UpdateOrderRequestDto request,
            @RequestHeader(value = "username", required = true) String username
    ){
        return CommonResponse.success(orderService.updateOrder(username, orderId, request));
    }
}
