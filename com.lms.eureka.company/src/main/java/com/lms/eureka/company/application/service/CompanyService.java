package com.lms.eureka.company.application.service;

import com.lms.eureka.company.application.dto.CompanyReadResponse;
import com.lms.eureka.company.application.dto.CompanyUpdateResponse;
import com.lms.eureka.company.domain.model.Company;
import com.lms.eureka.company.domain.repository.CompanyRepository;
import com.lms.eureka.company.infrastructure.repository.JpaCompanyRepository;
import com.lms.eureka.company.infrastructure.repository.QueryDslCompanyRepository;
import com.lms.eureka.company.presentation.request.CompanyCreateRequest;
import com.lms.eureka.company.presentation.request.CompanyDeleteRequest;
import com.lms.eureka.company.presentation.request.CompanyUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Transactional
    public void createCompany(CompanyCreateRequest companyCreateRequest) {

        if (companyRepository.findByName(companyCreateRequest.name()).isPresent()) {
            throw new RuntimeException("중복된 업체의 이름입니다.");
        }
        companyRepository.save(new Company(companyCreateRequest));
    }

    @Transactional(readOnly = true)
    public Page<CompanyReadResponse> findCompanies(String search, Pageable pageable) {
        if (search == null || search.trim().isEmpty()) {
            return companyRepository.findCompaniesBy(pageable);
        }
        return companyRepository.findCompaniesByName(search, pageable);
    }

    @Transactional(readOnly = true)
    public CompanyReadResponse findCompany(UUID companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("유효한 업체 id가 아닙니다."));

        return new CompanyReadResponse(company.getId(), company.getName());
    }

    @Transactional
    public CompanyUpdateResponse modifyCompany(UUID companyId, CompanyUpdateRequest companyUpdateRequest) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("유효한 업체 id가 아닙니다."));
        company.update(companyUpdateRequest);

        return new CompanyUpdateResponse(company.getId(), company.getName());
    }

    @Transactional
    public String deleteCompany(CompanyDeleteRequest companyDeleteRequest) {
        Company company = companyRepository.findById(companyDeleteRequest.id())
                .orElseThrow(() -> new RuntimeException("유효한 업체 id가 아닙니다."));
        company.delete();
        return companyDeleteRequest.id() + " 업체 삭제 완료.";
    }
}
