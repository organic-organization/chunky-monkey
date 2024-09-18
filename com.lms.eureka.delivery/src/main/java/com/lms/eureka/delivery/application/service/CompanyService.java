package com.lms.eureka.delivery.application.service;

import com.lms.eureka.delivery.application.client.CompanyClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyClient companyClient;

    public Map<UUID, UUID> getCompany(UUID requestCompanyId, UUID recipientCompanyId){
        Map<UUID, UUID> map = new HashMap<>();
        map.put(requestCompanyId, companyClient.getCompany(requestCompanyId).getData());
        map.put(recipientCompanyId, companyClient.getCompany(recipientCompanyId).getData());

        return map;
    }
}
