package com.lms.eureka.company.presentation.controller;

import com.lms.eureka.company.application.service.CompanyService;
import com.lms.eureka.company.presentation.request.*;
import com.lms.eureka.company.presentation.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/company-manager/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final HubClient hubClient;

    @PostMapping("")
    public CommonResponse createCompany(@RequestBody CompanyCreateRequest companyCreateRequest) {

        // requestBody에 있는 hubId 값을 가지는 hub가 db에 존재하지 않을 경우 예외를 throw
        CommonResponse hubResponse = hubClient.findHubById(companyCreateRequest.hubId());

        return CommonResponse
                .success(companyService.createCompany(companyCreateRequest));
    }

    @GetMapping("")
    public CommonResponse getCompanies(@PageableDefault(page = 0, size = 10, sort = {"name"}, direction = Sort.Direction.DESC) Pageable pageable,
                                          @RequestParam(name = "search", required = false) String search) {

        // 업체 목록 조회 시 한 번에 10, 30, 50개 단위로만 조회 가능
        if (!List.of(10, 30, 50).contains(pageable.getPageSize())) {
            return CommonResponse
                    .failure("한 페이지 당 10, 30, 50개 단위로만 조회가 가능합니다.");
        }

        return CommonResponse
                .success(HttpStatus.OK, "업체 추가 완료", companyService.findCompanies(search, pageable));
    }

    @GetMapping("/{companyId}")
    public CommonResponse getCompany(@PathVariable(name = "companyId") UUID companyId) {
        return CommonResponse
                .success(HttpStatus.OK, "업체 조회 완료", companyService.findCompany(companyId));
    }

    @PutMapping("/{companyId}")
    public CommonResponse modifyCompany(@PathVariable(name = "companyId") UUID companyId,
                                                               @RequestBody CompanyUpdateRequest companyUpdateRequest) {
        return CommonResponse
                .success(HttpStatus.OK, "업체 수정 완료", companyService.modifyCompany(companyId, companyUpdateRequest));
    }

    @DeleteMapping("")
    public CommonResponse deleteCompany(@RequestBody CompanyDeleteRequest companyDeleteRequest) {
        return CommonResponse
                .success(HttpStatus.OK, companyService.deleteCompany(companyDeleteRequest));
    }

    @PostMapping("/{companyId}/products")
    public CommonResponse createCompanyProducts(@PathVariable(name = "companyId") UUID companyId,
                                                        @RequestBody List<CompanyProductCreateRequest> companyProductCreateRequests) {
        return CommonResponse
                .success(HttpStatus.OK, companyService.createCompanyProduct(companyId, companyProductCreateRequests));
    }

    @GetMapping("/{companyId}/products")
    public CommonResponse getCompanyProducts(@PathVariable(name = "companyId") UUID companyId,
                                                @PageableDefault(page = 0, size = 10, sort = {"name"}, direction = Sort.Direction.DESC) Pageable pageable,
                                                @RequestParam(name = "search", required = false) String search) {
        // 상품 목록 조회 시 한 번에 10, 30, 50개 단위로만 조회 가능
        if (!List.of(10, 30, 50).contains(pageable.getPageSize())) {
            return CommonResponse
                    .failure("한 페이지 당 10, 30, 50개 단위로만 조회가 가능합니다.");
        }

        return CommonResponse
                .success(HttpStatus.OK, companyService.findCompanyProducts(companyId, search, pageable));
    }

    @PutMapping("/{companyId}/products/{productId}")
    public CommonResponse modifyCompanyProducts(@PathVariable(name = "companyId") UUID companyId,
                                                @PathVariable(name = "productId") UUID productId,
                                                @RequestBody CompanyProductUpdateRequest companyProductUpdateRequest) {
        return CommonResponse
                .success(HttpStatus.OK, "상품 수정 완료", companyService.modifyCompanyProduct(companyId, productId, companyProductUpdateRequest));
    }

}