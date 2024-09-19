package com.lms.eureka.hub.presentation.controller.hub;

import static com.lms.eureka.hub.domain.success.HubSuccessCase.DELIVERY_AGENT_CREATE;
import static com.lms.eureka.hub.domain.success.HubSuccessCase.HUB_CREATE;
import static com.lms.eureka.hub.domain.success.HubSuccessCase.HUB_DELETE;
import static com.lms.eureka.hub.domain.success.HubSuccessCase.HUB_MANAGER_CREATE;

import com.lms.eureka.hub.application.dto.deliveryAgent.DeliveryAgentDto;
import com.lms.eureka.hub.application.dto.hubManager.HubManagerDto;
import com.lms.eureka.hub.presentation.reponse.CommonResponse;
import com.lms.eureka.hub.application.dto.hub.HubDto;
import com.lms.eureka.hub.application.service.HubService;
import com.lms.eureka.hub.presentation.request.hub.CreateDeliveryAgentRequest;
import com.lms.eureka.hub.presentation.request.hub.CreateHubManagerRequest;
import com.lms.eureka.hub.presentation.request.hub.CreateHubRequest;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthHubController {

    private final HubService hubService;

    @PostMapping("/master/hubs")
    public CommonResponse createHub(@Valid @RequestBody CreateHubRequest requestParam,
                                    @RequestHeader(value = "username", required = true) String username) {
        HubDto hubDto = hubService.createHub(requestParam, username);
        return CommonResponse.success(HUB_CREATE.getMessage(), hubDto);
    }

    @DeleteMapping("/master/hubs/{hubId}")
    public CommonResponse deleteHub(@Valid @PathVariable("hubId") UUID hubId,
                                    @RequestHeader(value = "username", required = true) String username) {
        HubDto hubDto = hubService.deleteHub(hubId, username);
        return CommonResponse.success(HUB_DELETE.getMessage(), hubDto);
    }

    @PostMapping("/master/hubs/{hubId}/hub-manager")
    public CommonResponse createHubManager(@Valid @PathVariable("hubId") UUID hubId,
                                           @Valid @RequestBody CreateHubManagerRequest requestParam,
                                           @RequestHeader(value = "username", required = true) String username) {
        HubManagerDto hubManagerDto = hubService.createHubManager(hubId, requestParam, username);
        return CommonResponse.success(HUB_MANAGER_CREATE.getMessage(), hubManagerDto);
    }

    @PostMapping("/master/hubs/{hubId}/delivery-agent")
    public CommonResponse createDeliveryAgent(@Valid @PathVariable("hubId") UUID hubId,
                                           @Valid @RequestBody CreateDeliveryAgentRequest requestParam,
                                           @RequestHeader(value = "username", required = true) String username) {
        DeliveryAgentDto deliveryAgentDto = hubService.createDeliveryAgent(hubId, requestParam, username);
        return CommonResponse.success(DELIVERY_AGENT_CREATE.getMessage(), deliveryAgentDto);
    }

}
