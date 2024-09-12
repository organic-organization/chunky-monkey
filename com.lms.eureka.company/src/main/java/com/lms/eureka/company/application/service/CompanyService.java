package com.lms.eureka.company.application.service;

import com.lms.eureka.company.application.dto.CompanyManagerReadResponse;
import com.lms.eureka.company.application.dto.CompanyProductReadResponse;
import com.lms.eureka.company.application.dto.CompanyReadResponse;
import com.lms.eureka.company.application.dto.CompanyUpdateResponse;
import com.lms.eureka.company.domain.model.Company;
import com.lms.eureka.company.domain.model.CompanyManager;
import com.lms.eureka.company.domain.model.Product;
import com.lms.eureka.company.domain.repository.CompanyRepository;
import com.lms.eureka.company.domain.repository.ManagerRepository;
import com.lms.eureka.company.domain.repository.ProductRepository;
import com.lms.eureka.company.presentation.request.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ProductRepository productRepository;
    private final ManagerRepository managerRepository;

    @Transactional
    public String createCompany(CompanyCreateRequest companyCreateRequest, String username) {

        if (companyRepository.findByName(companyCreateRequest.name()).isPresent()) {
            throw new RuntimeException("중복된 업체의 이름입니다.");
        }
        companyRepository.save(new Company(companyCreateRequest, username));
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
    public CompanyUpdateResponse modifyCompany(UUID companyId, CompanyUpdateRequest companyUpdateRequest, String username) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException(companyId + "는 유효한 업체 id가 아닙니다."));
        company.update(companyUpdateRequest, username);

        return new CompanyUpdateResponse(company.getId(), company.getName(), company.getCreatedAt(), company.getUpdatedAt());
    }

    @Transactional
    public String deleteCompany(CompanyDeleteRequest companyDeleteRequest, String username) {
        Company company = companyRepository.findById(companyDeleteRequest.id())
                .orElseThrow(() -> new RuntimeException(companyDeleteRequest.id() + "는 유효한 업체 id가 아닙니다."));

        company.delete(username);
        return companyDeleteRequest.id() + " 업체 삭제 완료.";
    }

    @Transactional
    public String createCompanyProduct(UUID companyId, List<CompanyProductCreateRequest> companyProductCreateRequests, String username) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException(companyId + "는 유효한 업체 id가 아닙니다."));
        companyProductCreateRequests
                .forEach(companyProductCreateRequest -> {
                    productRepository.save(new Product(companyProductCreateRequest, company, username));
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
    public CompanyProductReadResponse modifyCompanyProduct(UUID companyId, UUID productId, CompanyProductUpdateRequest companyProductUpdateRequest, String username) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException(companyId + "는 유효한 업체 id가 아닙니다."));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException(productId + "는 유효한 상품 id가 아닙니다."));

        product.update(companyProductUpdateRequest, username);
        return new CompanyProductReadResponse(product.getId(), product.getName(), product.getPrice(), product.getStock(), product.getCreatedAt(), product.getUpdatedAt());
    }

    @Transactional
    public String deleteCompanyProducts(UUID companyId, List<CompanyProductDeleteRequest> companyProductDeleteRequests, String username) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException(companyId + "는 유효한 업체 id가 아닙니다."));

        companyProductDeleteRequests
                .forEach(companyProductDeleteRequest -> {
                    Product product = productRepository.findById(companyProductDeleteRequest.productId())
                            .orElseThrow(() -> new RuntimeException(companyProductDeleteRequest.productId() + "는 유효한 상품 id가 아닙니다."));
                    product.delete(username);
                });
        return "상품 삭제 완료.";
    }

    @Transactional
    public String createCompanyManager(UUID companyId, CompanyManagerCreateRequest companyManagerCreateRequest, String username) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException(companyId + "는 유효한 업체 id가 아닙니다."));
        CompanyManager companyManager = new CompanyManager(company, companyManagerCreateRequest.userId(), username);
        managerRepository.save(companyManager);
        return "업체 관리자 추가 완료.";
    }

    @Transactional
    public Page<CompanyManagerReadResponse> findCompanyManagers(UUID companyId, Pageable pageable) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException(companyId + "는 유효한 업체 id가 아닙니다."));
        return managerRepository.findCompanyManagersBy(pageable);
    }

    @Transactional
    public CompanyManagerReadResponse findCompanyManager(UUID companyId, UUID managerId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException(companyId + "는 유효한 업체 id가 아닙니다."));
        CompanyManager companyManager = managerRepository.findById(managerId)
                .orElseThrow(() -> new RuntimeException(managerId + "는 유효한 업체 담당자 id가 아닙니다."));
        return new CompanyManagerReadResponse(companyManager.getId(), company.getId(), companyManager.getUserId());
    }

    @Transactional
    public String deleteCompanyManagers(UUID companyId, List<CompanyManagerDeleteRequest> companyManagerDeleteRequests, String username) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException(companyId + "는 유효한 업체 id가 아닙니다."));
        companyManagerDeleteRequests
                .forEach(companyManagerDeleteRequest -> {
                    UUID managerId = companyManagerDeleteRequest.id();
                    CompanyManager companyManager = managerRepository.findById(managerId)
                            .orElseThrow(() -> new RuntimeException(managerId + "는 유효한 업체 담당자 id가 아닙니다."));
                    companyManager.delete(username);
                });
        return "업체 담당자 삭제 완료.";
    }
}
