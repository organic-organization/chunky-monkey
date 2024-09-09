package com.lms.eureka.company.domain.model;

import com.lms.eureka.company.presentation.request.CompanyCreateRequest;
import com.lms.eureka.company.presentation.request.CompanyUpdateRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "p_company")
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "company_id")
    private UUID id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    @Enumerated(EnumType.STRING)
    private CompanyType type;

    @OneToMany(mappedBy = "company")
    private List<Product> products = new ArrayList<>();

    @Column
    private UUID hubId;

    public Company(CompanyCreateRequest companyCreateRequest) {
        this.name = companyCreateRequest.name();
        this.address = companyCreateRequest.address();
        this.hubId = companyCreateRequest.hubId();
    }

    public void update(CompanyUpdateRequest companyUpdateRequest) {
        this.name = companyUpdateRequest.name();
    }

    public void delete() {
//        this.isDeleted = true;
    }

}