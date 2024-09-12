package com.lms.eureka.hub.presentation.controller;

import static com.lms.eureka.hub.domain.success.HubSuccessCase.HUB_CREATE;
import static com.lms.eureka.hub.domain.success.HubSuccessCase.HUB_DELETE;

import com.lms.eureka.hub.presentation.reponse.CommonResponse;
import com.lms.eureka.hub.application.dto.hub.HubDto;
import com.lms.eureka.hub.application.service.HubService;
import com.lms.eureka.hub.presentation.request.CreateHubRequest;
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

}
