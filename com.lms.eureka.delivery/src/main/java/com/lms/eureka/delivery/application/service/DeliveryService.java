package com.lms.eureka.delivery.application.service;

import com.lms.eureka.delivery.application.dto.DeliveryDto;
import com.lms.eureka.delivery.application.dto.DeliveryRouteDto;
import com.lms.eureka.delivery.application.dto.HubRouteDto;
import com.lms.eureka.delivery.domain.service.DeliveryDomainService;
import com.lms.eureka.delivery.domain.service.DeliveryRouteDomainService;
import com.lms.eureka.delivery.presentation.request.CreateDeliveryRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final DeliveryDomainService deliveryDomainService;
    private final HubService hubService;
    private final CompanyService companyService;
    private final DeliveryRouteDomainService  deliveryRouteDomainService;

    public void createDelivery(CreateDeliveryRequestDto request) {
        // companyId를 통한 소속 허브 정보 가져오기
        Map<UUID, UUID> companyHubMap = companyService.getCompany(request.requestCompanyId(), request.recipientCompanyId());

        // 해당 정보를 통해 delivery 저장
        DeliveryDto saveDeliveryDto = deliveryDomainService.createDelivery(DeliveryDto.create(request, companyHubMap));

        //배송 경로 기록 조회
        HubRouteDto hubRouteDto = hubService.getHubRoutes(saveDeliveryDto.startHubId(), saveDeliveryDto.endHubId());

        // 배송 경로 기록 저장
        deliveryRouteDomainService.createDeliveryRoute(DeliveryRouteDto.create(saveDeliveryDto, hubRouteDto));
    }
}
