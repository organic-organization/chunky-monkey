package com.lms.eureka.company.presentation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lms.eureka.company.application.service.CompanyService;
import com.lms.eureka.company.presentation.request.*;
import com.lms.eureka.company.presentation.response.CommonResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/company-manager/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final HubClient hubClient;
    private final UserClient userClient;

    @PostMapping("")
    public CommonResponse createCompany(@RequestBody CompanyCreateRequest companyCreateRequest,
                                        HttpServletRequest request) {

        // [1] hubId 검증
        CommonResponse hubResponse = hubClient.findHub(companyCreateRequest.hubId());
        if (!hubResponse.getStatus().equals(HttpStatus.OK)) {
            return CommonResponse
                    .failure(HttpStatus.NOT_FOUND, "허브 조회 실패: hub_id 값이 존재하지 않음.");
        }

        // [2] username 검증
        String username = request.getHeader("username");
        CommonResponse userResponse = userClient.getUserByUsername(username);
        if(!userResponse.getStatus().equals(HttpStatus.OK)) {
            return CommonResponse
                    .failure(HttpStatus.NOT_FOUND, "사용자 조회 실패: username 값이 존재하지 않음.");
        }
        return CommonResponse
                .success(companyService.createCompany(companyCreateRequest, username));
    }

    @GetMapping("")
    public CommonResponse getCompanies(@PageableDefault(page = 0, size = 10, sort = {"updatedAt"}, direction = Sort.Direction.DESC) Pageable pageable,
                                          @RequestParam(name = "search", required = false) String search) {

        // 업체 목록 조회 시 한 번에 10, 30, 50개 단위로만 조회 가능
        if (!List.of(10, 30, 50).contains(pageable.getPageSize())) {
            return CommonResponse
                    .failure(HttpStatus.BAD_REQUEST, "한 페이지 당 10, 30, 50개 단위로만 조회가 가능합니다.");
        }

        return CommonResponse
                .success("업체 목록 조회 완료", companyService.findCompanies(search, pageable));
    }

    @GetMapping("/{companyId}")
    public CommonResponse getCompany(@PathVariable(name = "companyId") UUID companyId) {
        return CommonResponse
                .success("단일 업체 조회 완료", companyService.findCompany(companyId));
    }

    @PutMapping("/{companyId}")
    public CommonResponse modifyCompany(@PathVariable(name = "companyId") UUID companyId,
                                        @RequestBody CompanyUpdateRequest companyUpdateRequest,
                                        HttpServletRequest request) {
        String username = request.getHeader("username");
        CommonResponse userResponse = userClient.getUserByUsername(username);
        if(!userResponse.getStatus().equals(HttpStatus.OK)) {
            return CommonResponse
                    .failure(HttpStatus.NOT_FOUND, "사용자 조회 실패: username 값이 존재하지 않음.");
        }
        return CommonResponse
                .success("업체 수정 완료", companyService.modifyCompany(companyId, companyUpdateRequest, username));
    }

    @DeleteMapping("")
    public CommonResponse deleteCompany(@RequestBody CompanyDeleteRequest companyDeleteRequest,
                                        HttpServletRequest request) {
        String username = request.getHeader("username");
        CommonResponse userResponse = userClient.getUserByUsername(username);
        if(!userResponse.getStatus().equals(HttpStatus.OK)) {
            return CommonResponse
                    .failure(HttpStatus.NOT_FOUND, "사용자 조회 실패: username 값이 존재하지 않음.");
        }
        return CommonResponse
                .success("업체 삭제 완료.", companyService.deleteCompany(companyDeleteRequest, username));
    }

    @PostMapping("/{companyId}/products")
    public CommonResponse createCompanyProducts(@PathVariable(name = "companyId") UUID companyId,
                                                @RequestBody List<CompanyProductCreateRequest> companyProductCreateRequests,
                                                HttpServletRequest request) {
        String username = request.getHeader("username");
        CommonResponse userResponse = userClient.getUserByUsername(username);
        if(!userResponse.getStatus().equals(HttpStatus.OK)) {
            return CommonResponse
                    .failure(HttpStatus.NOT_FOUND, "사용자 조회 실패: username 값이 존재하지 않음.");
        }
        return CommonResponse
                .success("상품 추가 완료.", companyService.createCompanyProduct(companyId, companyProductCreateRequests, username));
    }

    @GetMapping("/{companyId}/products")
    public CommonResponse getCompanyProducts(@PathVariable(name = "companyId") UUID companyId,
                                             @PageableDefault(page = 0, size = 10, sort = {"updatedAt"}, direction = Sort.Direction.DESC) Pageable pageable,
                                             @RequestParam(name = "search", required = false) String search) {
        // 상품 목록 조회 시 한 번에 10, 30, 50개 단위로만 조회 가능
        if (!List.of(10, 30, 50).contains(pageable.getPageSize())) {
            return CommonResponse
                    .failure(HttpStatus.BAD_REQUEST, "한 페이지 당 10, 30, 50개 단위로만 조회가 가능합니다.");
        }

        return CommonResponse
                .success("상품 조회 완료.", companyService.findCompanyProducts(companyId, search, pageable));
    }

    @PutMapping("/{companyId}/products/{productId}")
    public CommonResponse modifyCompanyProduct(@PathVariable(name = "companyId") UUID companyId,
                                                @PathVariable(name = "productId") UUID productId,
                                                @RequestBody CompanyProductUpdateRequest companyProductUpdateRequest,
                                               HttpServletRequest request) {
        String username = request.getHeader("username");
        CommonResponse userResponse = userClient.getUserByUsername(username);
        if(!userResponse.getStatus().equals(HttpStatus.OK)) {
            return CommonResponse
                    .failure(HttpStatus.NOT_FOUND, "사용자 조회 실패: username 값이 존재하지 않음.");
        }
        return CommonResponse
                .success("상품 수정 완료", companyService.modifyCompanyProduct(companyId, productId, companyProductUpdateRequest, username));
    }

    @DeleteMapping("/{companyId}/products")
    public CommonResponse deleteCompanyProducts(@PathVariable(name = "companyId") UUID companyId,
                                                @RequestBody List<CompanyProductDeleteRequest> companyProductDeleteRequests,
                                                HttpServletRequest request) {
        String username = request.getHeader("username");
        CommonResponse userResponse = userClient.getUserByUsername(username);
        if(!userResponse.getStatus().equals(HttpStatus.OK)) {
            return CommonResponse
                    .failure(HttpStatus.NOT_FOUND, "사용자 조회 실패: username 값이 존재하지 않음.");
        }
        return CommonResponse
                .success(companyService.deleteCompanyProducts(companyId, companyProductDeleteRequests, username));
    }

    @PostMapping("/{companyId}/managers")
    public CommonResponse createCompanyManager(@PathVariable(name = "companyId") UUID companyId,
                                               @RequestBody CompanyManagerCreateRequest companyManagerCreateRequest,
                                               HttpServletRequest request) {
        Long userId = companyManagerCreateRequest.userId();
        userClient.getUserByUserId(userId);

        String username = request.getHeader("username");
        CommonResponse userResponse = userClient.getUserByUsername(username);
        if(!userResponse.getStatus().equals(HttpStatus.OK)) {
            return CommonResponse
                    .failure(HttpStatus.NOT_FOUND, "사용자 조회 실패: username 값이 존재하지 않음.");
        }
        return CommonResponse
                .success(companyService.createCompanyManager(companyId, companyManagerCreateRequest, username));
    }

    @GetMapping("/{companyId}/managers")
    public CommonResponse getCompanyManagers(@PathVariable(name = "companyId") UUID companyId,
                                             @PageableDefault(page = 0, size = 10, sort = {"updatedAt"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return CommonResponse
                .success("업체 담당자 목록 조회 완료.", companyService.findCompanyManagers(companyId, pageable));
    }

    @GetMapping("/{companyId}/managers/{managerId}")
    public CommonResponse getCompanyManager(@PathVariable(name = "companyId") UUID companyId,
                                            @PathVariable(name = "managerId") UUID managerId) {
        return CommonResponse
                .success("업체 담당자 조회 완료.", companyService.findCompanyManager(companyId, managerId));
    }

    @DeleteMapping("/{companyId}/managers")
    public CommonResponse deleteCompanyManager(@PathVariable(name = "companyId") UUID companyId,
                                               @RequestBody List<CompanyManagerDeleteRequest> companyManagerDeleteRequests,
                                               HttpServletRequest request) {
        String username = request.getHeader("username");
        CommonResponse userResponse = userClient.getUserByUsername(username);
        if(!userResponse.getStatus().equals(HttpStatus.OK)) {
            return CommonResponse
                    .failure(HttpStatus.NOT_FOUND, "사용자 조회 실패: username 값이 존재하지 않음.");
        }
        return CommonResponse
                .success("업체 담당자 삭제 완료", companyService.deleteCompanyManagers(companyId, companyManagerDeleteRequests, username));
    }

}