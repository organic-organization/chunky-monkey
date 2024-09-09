package com.lms.eureka.company.domain.repository;

import com.lms.eureka.company.domain.model.Company;
import com.lms.eureka.company.infrastructure.repository.JpaCompanyRepository;
import com.lms.eureka.company.infrastructure.repository.QueryDslCompanyRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID>, QueryDslCompanyRepository {
    Optional<Company> findByName(@Param("name") String name);
}