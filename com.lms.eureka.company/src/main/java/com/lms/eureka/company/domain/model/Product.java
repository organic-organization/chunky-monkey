package com.lms.eureka.company.domain.model;

import com.lms.eureka.company.presentation.request.CompanyProductCreateRequest;
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

    public Product(CompanyProductCreateRequest companyProductCreateRequest, Company company) {
        this.name = companyProductCreateRequest.name();
        this.price = companyProductCreateRequest.price();
        this.stock = companyProductCreateRequest.stock();
        this.company = company;
        company.getProducts().add(this);
    }
}
