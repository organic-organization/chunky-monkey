package com.lms.eureka.company.infrastructure.repository;

import com.lms.eureka.company.application.dto.CompanyProductReadResponse;
import com.lms.eureka.company.application.dto.CompanyReadResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface QueryDslProductRepository {
    Page<CompanyProductReadResponse> findCompanyProductsBy(UUID companyId, Pageable pageable);

    Page<CompanyProductReadResponse> findCompanyProductsByName(String search, UUID companyId, Pageable pageable);
}
