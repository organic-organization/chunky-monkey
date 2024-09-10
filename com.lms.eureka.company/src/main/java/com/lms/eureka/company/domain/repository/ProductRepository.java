package com.lms.eureka.company.domain.repository;

import com.lms.eureka.company.domain.model.Product;
import com.lms.eureka.company.infrastructure.repository.QueryDslProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID>, QueryDslProductRepository {
}
