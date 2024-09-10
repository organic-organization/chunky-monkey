package com.lms.eureka.company.application.service;

import com.lms.eureka.company.application.dto.CompanyProductReadResponse;
import com.lms.eureka.company.application.dto.CompanyReadResponse;
import com.lms.eureka.company.application.dto.CompanyUpdateResponse;
import com.lms.eureka.company.domain.model.Company;
import com.lms.eureka.company.domain.model.Product;
import com.lms.eureka.company.domain.repository.CompanyRepository;
import com.lms.eureka.company.domain.repository.ProductRepository;
import com.lms.eureka.company.presentation.request.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ProductRepository productRepository;

    @Transactional
    public String createCompany(CompanyCreateRequest companyCreateRequest) {

        if (companyRepository.findByName(companyCreateRequest.name()).isPresent()) {
            throw new RuntimeException("중복된 업체의 이름입니다.");
        }
        companyRepository.save(new Company(companyCreateRequest));
        return "업체 추가 완료.";
    }

    @Transactional(readOnly = true)
    public Page<CompanyReadResponse> findCompanies(String search, Pageable pageable) {
        if (search == null || search.trim().isEmpty()) {
            return companyRepository.findCompaniesBy(pageable);
        }
        return companyRepository.findCompaniesByName(search, pageable);
    }

    @Transactional(readOnly = true)
    public CompanyReadResponse findCompany(UUID companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException(companyId + "는 유효한 업체 id가 아닙니다."));

        return new CompanyReadResponse(company.getId(), company.getName(), company.getCreatedAt());
    }

    @Transactional
    public CompanyUpdateResponse modifyCompany(UUID companyId, CompanyUpdateRequest companyUpdateRequest) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException(companyId + "는 유효한 업체 id가 아닙니다."));
        company.update(companyUpdateRequest);

        return new CompanyUpdateResponse(company.getId(), company.getName(), company.getCreatedAt(), company.getUpdatedAt());
    }

    @Transactional
    public String deleteCompany(CompanyDeleteRequest companyDeleteRequest) {
        Company company = companyRepository.findById(companyDeleteRequest.id())
                .orElseThrow(() -> new RuntimeException(companyDeleteRequest.id() + "는 유효한 업체 id가 아닙니다."));
        company.delete("name");
        return companyDeleteRequest.id() + " 업체 삭제 완료.";
    }

    @Transactional
    public String createCompanyProduct(UUID companyId, List<CompanyProductCreateRequest> companyProductCreateRequests) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException(companyId + "는 유효한 업체 id가 아닙니다."));
        companyProductCreateRequests
                .forEach(companyProductCreateRequest -> {
                    productRepository.save(new Product(companyProductCreateRequest, company));
                });
        return "상품 추가 완료.";
    }

    @Transactional
    public Page<CompanyProductReadResponse> findCompanyProducts(UUID companyId, String search, Pageable pageable) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException(companyId + "는 유효한 업체 id가 아닙니다."));
        if (search == null || search.trim().isEmpty()) {
            return productRepository.findCompanyProductsBy(companyId, pageable);
        }
        return productRepository.findCompanyProductsByName(search, companyId, pageable);
    }

    @Transactional
    public CompanyProductReadResponse modifyCompanyProduct(UUID companyId, UUID productId, CompanyProductUpdateRequest companyProductUpdateRequest) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException(companyId + "는 유효한 업체 id가 아닙니다."));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException(productId + "는 유효한 상품 id가 아닙니다."));

        product.update(companyProductUpdateRequest);
        return new CompanyProductReadResponse(product.getId(), product.getName(), product.getPrice(), product.getStock(), product.getCreatedAt(), product.getUpdatedAt());
    }

    @Transactional
    public String deleteCompanyProducts(UUID companyId, List<CompanyProductDeleteRequest> companyProductDeleteRequests) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException(companyId + "는 유효한 업체 id가 아닙니다."));

        companyProductDeleteRequests
                .forEach(companyProductDeleteRequest -> {
                    Product product = productRepository.findById(companyProductDeleteRequest.productId())
                            .orElseThrow(() -> new RuntimeException(companyProductDeleteRequest.productId() + "는 유효한 상품 id가 아닙니다."));
                    product.delete("name");
                });
        return "상품 삭제 완료";
    }
}
