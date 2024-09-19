package com.lms.eureka.company.infrastructure.repository;

import com.lms.eureka.company.application.dto.CompanyManagerReadResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QueryDslManagerRepository {
    Page<CompanyManagerReadResponse> findCompanyManagersBy(Pageable pageable);
}
