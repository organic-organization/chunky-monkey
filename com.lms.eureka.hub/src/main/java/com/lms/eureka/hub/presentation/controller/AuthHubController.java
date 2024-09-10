package com.lms.eureka.hub.presentation.controller;

import static com.lms.eureka.hub.domain.success.HubSuccessCase.HUB_CREATE;

import com.lms.eureka.hub.presentation.reponse.CommonResponse;
import com.lms.eureka.hub.application.dto.hub.HubDto;
import com.lms.eureka.hub.application.service.HubService;
import com.lms.eureka.hub.presentation.request.CreateHubRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public CommonResponse createHub(@RequestHeader(value = "username", required = true) String username,
                                    @Valid @RequestBody CreateHubRequest requestParam) {
        HubDto hubDto = hubService.createHub(username, requestParam);
        return CommonResponse.success(HUB_CREATE.getMessage(), hubDto);
    }

}
