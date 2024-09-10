package com.lms.eureka.hub.presentation.controller;

import static com.lms.eureka.hub.domain.success.HubSuccessCase.HUB_FIND;
import static com.lms.eureka.hub.domain.success.HubSuccessCase.HUB_SEARCH;

import com.lms.eureka.hub.application.dto.hub.HubDto;
import com.lms.eureka.hub.application.service.HubService;
import com.lms.eureka.hub.presentation.reponse.CommonResponse;
import com.lms.eureka.hub.presentation.request.SearchHubRequest;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @GetMapping("/search")
    public CommonResponse searchHub(@Valid @ModelAttribute SearchHubRequest requestParam, Pageable pageable) {
        Page<HubDto> hubDtoPage = hubService.searchHub(requestParam, pageable);
        return CommonResponse.success(HUB_SEARCH.getMessage(), hubDtoPage);
    }

}
