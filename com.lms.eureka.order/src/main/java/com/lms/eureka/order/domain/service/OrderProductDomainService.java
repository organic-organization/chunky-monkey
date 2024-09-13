package com.lms.eureka.order.domain.service;

import com.lms.eureka.order.application.dto.OrderDto;
import com.lms.eureka.order.application.dto.OrderProductDto;
import com.lms.eureka.order.domain.entity.OrderProduct.OrderProduct;
import com.lms.eureka.order.domain.repository.OrderProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderProductDomainService {
    private final OrderProductRepository orderProductRepository;

    public void createOrderProduct(String username, OrderDto saveOrderDto, List<OrderProductDto> list) {
        List<OrderProduct> saveOrderProducts = orderProductRepository.saveAll(
                list.stream().map(dto -> dto.toEntity(username)).toList()
        );
    }
}
