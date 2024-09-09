package com.lms.eureka.company.infrastructure.repository;

import com.lms.eureka.company.domain.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface JpaCompanyRepository extends JpaRepository<Company, UUID> {

    Optional<Company> findByName(@Param("name") String name);

}