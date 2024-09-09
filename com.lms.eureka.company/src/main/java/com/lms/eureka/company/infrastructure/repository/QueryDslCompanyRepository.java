package com.lms.eureka.company.infrastructure.repository;

import com.lms.eureka.company.application.dto.CompanyReadResponse;
import com.lms.eureka.company.domain.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface QueryDslCompanyRepository {

    Page<CompanyReadResponse> findCompaniesBy(Pageable pageable);

    Page<CompanyReadResponse> findCompaniesByName(String search, Pageable pageable);
}