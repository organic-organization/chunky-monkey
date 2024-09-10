package com.lms.eureka.hub.application.service;

import com.lms.eureka.hub.application.client.UserClient;
import com.lms.eureka.hub.application.dto.hub.HubDto;
import com.lms.eureka.hub.application.dto.hub.HubMapper;
import com.lms.eureka.hub.domain.entity.hub.Hub;
import com.lms.eureka.hub.domain.exception.HubException;
import com.lms.eureka.hub.domain.exception.HubExceptionCase;
import com.lms.eureka.hub.domain.service.HubDomainService;
import com.lms.eureka.hub.presentation.request.CreateHubRequest;
import com.lms.eureka.hub.presentation.request.SearchHubRequest;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HubService {

    private final HubDomainService hubDomainService;
    private final HubMapper hubMapper;
    private final UserClient userClient;

    public HubDto createHub(CreateHubRequest requestParam, String username) {
        checkUserExists(username);
        Hub hub = hubDomainService.createHub(requestParam, username);
        return hubMapper.toDto(hub);
    }

    private void checkUserExists(String username) {
        if (!userClient.getProduct(username).getStatus().equals(HttpStatus.OK)) {
            throw new HubException(HubExceptionCase.USER_NOT_FOUND);
        }
    }

    public HubDto findHub(UUID hubId) {
        Hub hub = hubDomainService.findHub(hubId);
        return hubMapper.toDto(hub);
    }

    public Page<HubDto> searchHub(SearchHubRequest requestParam, Pageable pageable) {
        Page<Hub> hubPage = hubDomainService.searchHub(requestParam, pageable);
        return hubPage.map(hubMapper::toDto);
    }

    public HubDto deleteHub(UUID hubId, String username) {
        checkUserExists(username);
        Hub hub = hubDomainService.deleteHub(hubId, username);
        return hubMapper.toDto(hub);
    }

}
