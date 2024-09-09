package com.lms.eureka.hub.presentation.controller;

import static com.lms.eureka.hub.domain.success.HubSuccessCase.HUB_FIND;

import com.lms.eureka.hub.application.dto.HubDto;
import com.lms.eureka.hub.application.service.HubService;
import com.lms.eureka.hub.presentation.reponse.CommonResponse;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public/hubs")
public class PublicHubController {

    private final HubService hubService;

    @GetMapping("/{hubId}")
    public CommonResponse findHub(@Valid @PathVariable("hubId") UUID hubId) {
        HubDto hubDto = hubService.findHub(hubId);
        return CommonResponse.success(HUB_FIND.getMessage(), hubDto);
    }

}
