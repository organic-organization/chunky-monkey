package com.lms.eureka.company.domain.model;

import com.lms.eureka.company.presentation.request.CompanyProductCreateRequest;
import com.lms.eureka.company.presentation.request.CompanyProductUpdateRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "p_product")
@Entity
@Builder
public class Product extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String name;

    @Column
    private Integer price;

    @Column
    private Integer stock;

    @Column
    private Boolean isHidden;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    public Product(CompanyProductCreateRequest companyProductCreateRequest, Company company, String username) {
        this.name = companyProductCreateRequest.name();
        this.price = companyProductCreateRequest.price();
        this.stock = companyProductCreateRequest.stock();
        this.company = company;
        this.setCreatedBy(username);
        this.setUpdatedBy(username);
        company.getProducts().add(this);
    }

    public void update(CompanyProductUpdateRequest companyProductUpdateRequest, String username) {
        if (companyProductUpdateRequest.name() != null) {
            this.name = companyProductUpdateRequest.name();
        }
        if (companyProductUpdateRequest.price() != null) {
            this.price = companyProductUpdateRequest.price();
        }
        if (companyProductUpdateRequest.stock() != null) {
            this.stock = companyProductUpdateRequest.stock();
        }
        this.setUpdatedBy(username);
    }
}
