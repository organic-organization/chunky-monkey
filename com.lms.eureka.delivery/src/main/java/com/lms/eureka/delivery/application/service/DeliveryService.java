package com.lms.eureka.delivery.application.service;

import com.lms.eureka.delivery.application.dto.DeliveryDto;
import com.lms.eureka.delivery.application.dto.DeliveryRouteDto;
import com.lms.eureka.delivery.application.dto.HubRouteDto;
import com.lms.eureka.delivery.domain.service.DeliveryDomainService;
import com.lms.eureka.delivery.domain.service.DeliveryRouteDomainService;
import com.lms.eureka.delivery.presentation.request.CreateDeliveryRequestDto;
import com.lms.eureka.delivery.presentation.request.UpdateDeliveryStatusRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.nio.channels.FileChannel;
import java.util.List;
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
        List<UUID> hubDeliveryManagerList = hubService.getDeliveryManagerList(companyHubMap.get(request.requestCompanyId()));

        // 해당 허브에서 배정되지 않은 배송 담당자부터 배정
        UUID deliveryManagerId = deliveryRouteDomainService.getDeliveryRoutesByDeliveryManager(companyHubMap.get(request.requestCompanyId()));
        if(deliveryManagerId == null){
            deliveryManagerId = hubDeliveryManagerList.get(0);
        }

        // 해당 정보를 통해 delivery 저장
        DeliveryDto saveDeliveryDto = deliveryDomainService.createDelivery(DeliveryDto.create(request, companyHubMap));

        //배송 경로 기록 조회
        HubRouteDto hubRouteDto = hubService.getHubRoutes(saveDeliveryDto.startHubId(), saveDeliveryDto.endHubId());

        // 배송 경로 기록 저장
        deliveryRouteDomainService.createDeliveryRoute(DeliveryRouteDto.create(saveDeliveryDto, hubRouteDto, deliveryManagerId));
    }

    public void updateStatus(UUID deliveryId, UpdateDeliveryStatusRequestDto request) {
        deliveryDomainService.updateStatus(deliveryId, request.orderStatus());

        deliveryRouteDomainService.updateStatus(request.deliveryRouteId(), request.deliveryRouteStatus());
    }

    public Page<DeliveryDto> getDeliveriesByMaster(Pageable pageable) {
        return deliveryDomainService.getDeliveriesByMaster(pageable);
    }

    public Page<DeliveryDto> getDeliveriesByhubManager(String username, Pageable pageable) {
        UUID startHubId = hubService.getHubIdByHubManager(username);

        return deliveryDomainService.getDeliveriesByStartHubId(startHubId, pageable);
    }

    public Page<DeliveryRouteDto> getDeliveriesByDeliveryManager(UUID deliveryManagerId, Pageable pageable) {
        return deliveryRouteDomainService.getDeliveriesByDeliveryManager(deliveryManagerId, pageable);
    }

    public Page<DeliveryDto> getDeliveriesByCompanyManager(String username, Pageable pageable) {
        UUID companyId = companyService.getCompanyByUsername(username);

        return deliveryDomainService.getDeliveriesByCompanyManager(companyId, pageable);
    }
}
