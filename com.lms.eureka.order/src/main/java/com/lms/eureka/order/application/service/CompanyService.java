package com.lms.eureka.order.application.service;

import com.lms.eureka.order.application.client.CompanyClient;
import com.lms.eureka.order.domain.exception.OrderException;
import com.lms.eureka.order.domain.exception.OrderExceptionCase;
import com.lms.eureka.order.presentation.reponse.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyClient companyClient;

    public String getCompany(String username) {
        CommonResponse<String> response = companyClient.getCompany(username);

        if (!response.getStatus().equals(HttpStatus.OK)) {
            throw new OrderException(OrderExceptionCase.USER_NOT_FOUND);
        }

        return response.getData();
    }
}
