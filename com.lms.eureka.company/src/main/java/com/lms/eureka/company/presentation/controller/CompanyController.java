package com.lms.eureka.company.presentation.controller;

import com.lms.eureka.company.application.dto.CompanyReadResponse;
import com.lms.eureka.company.application.dto.CompanyUpdateResponse;
import com.lms.eureka.company.application.service.CompanyService;
import com.lms.eureka.company.domain.service.CompanyDomainService;
import com.lms.eureka.company.presentation.request.CompanyCreateRequest;
import com.lms.eureka.company.presentation.request.CompanyDeleteRequest;
import com.lms.eureka.company.presentation.request.CompanyUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> createCompany(@RequestBody CompanyCreateRequest companyCreateRequest) {

        // requestBody에 있는 hubId 값을 가지는 hub가 db에 존재하지 않을 경우 예외를 throw
//        hubClient.findHubById(companyCreateRequest.hubId());

        companyService.createCompany(companyCreateRequest);
        return ResponseEntity.ok()
                .body("업체 추가 완료.");
    }

    @GetMapping("")
    public ResponseEntity<?> getCompanies(@PageableDefault(page = 0, size = 10, sort = {"name"}, direction = Sort.Direction.DESC) Pageable pageable,
                                          @RequestParam(name = "search", required = false) String search) {

        // 업체 목록 조회 시 한 번에 10, 30, 50개 단위로만 조회 가능
        if (!List.of(10, 30, 50).contains(pageable.getPageSize())) {
            return ResponseEntity.badRequest()
                    .body("한 페이지 당 10, 30, 50개 단위로만 조회가 가능합니다.");
        }

        return ResponseEntity.ok()
                .body(companyService.findCompanies(search, pageable));
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyReadResponse> getCompany(@PathVariable(name = "companyId") UUID companyId) {
        return ResponseEntity.ok()
                .body(companyService.findCompany(companyId));
    }

    @PutMapping("/{companyId}")
    public ResponseEntity<CompanyUpdateResponse> modifyCompany(@PathVariable(name = "companyId") UUID companyId,
                                                               @RequestBody CompanyUpdateRequest companyUpdateRequest) {
        return ResponseEntity.ok()
                .body(companyService.modifyCompany(companyId, companyUpdateRequest));
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteCompany(@RequestBody CompanyDeleteRequest companyDeleteRequest) {
        return ResponseEntity.ok()
                .body(companyService.deleteCompany(companyDeleteRequest));
    }
/*
    @PostMapping("/{companyId}/products")
    public ResponseEntity<CompanyProductCreateResponse> createCompanyProducts(@PathVariable(name = "companyId") UUID companyId,
                                                                            @RequestBody List<CompanyProductCreateRequest> companyProductCreateRequests) {
        return ResponseEntity.ok()
                .body(companyService.createCompanyProduct(companyId, companyProductCreateRequests))
    }
*/

}