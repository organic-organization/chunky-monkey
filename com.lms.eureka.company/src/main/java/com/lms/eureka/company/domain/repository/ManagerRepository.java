package com.lms.eureka.company.domain.repository;

import com.lms.eureka.company.domain.model.CompanyManager;
import com.lms.eureka.company.infrastructure.repository.QueryDslManagerRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ManagerRepository extends JpaRepository<CompanyManager, UUID>, QueryDslManagerRepository {
}
