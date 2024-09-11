package com.lms.eureka.company.infrastructure.repository;

import com.lms.eureka.company.application.dto.CompanyReadResponse;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.lms.eureka.company.domain.model.QCompany.company;

@RequiredArgsConstructor
public class CompanyRepositoryImpl implements QueryDslCompanyRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<CompanyReadResponse> findCompaniesBy(Pageable pageable) {
        List<OrderSpecifier<?>> orders = getAllOrderSpecifiers();

        JPAQuery<Tuple> query = jpaQueryFactory
                .select(
                        company.id,
                        company.name,
                        company.createdAt
                )
                .from(company)
                .where(company.deletedBy.isNotNull())
                .orderBy(orders.toArray(new OrderSpecifier[0]))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<Tuple> results = query.fetch();
        List<CompanyReadResponse> companyReadResponses = new ArrayList<>();
        for (Tuple result: results) {
            companyReadResponses.add(tupleToResponse(result));
        }

        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(company.count())
                .from(company);
        return new PageImpl<>(companyReadResponses, pageable, countQuery.fetchCount());
    }

    @Override
    public Page<CompanyReadResponse> findCompaniesByName(String search, Pageable pageable) {
        List<OrderSpecifier<?>> orders = getAllOrderSpecifiers();

        JPAQuery<Tuple> query = jpaQueryFactory
                .select(
                        company.id,
                        company.name,
                        company.createdAt
                )
                .from(company)
                .where(company.deletedBy.isNotNull())
                .where(company.name.contains(search))
                .orderBy(orders.toArray(new OrderSpecifier[0]))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<Tuple> results = query.fetch();
        List<CompanyReadResponse> companyReadResponses = new ArrayList<>();
        for (Tuple result: results) {
            companyReadResponses.add(tupleToResponse(result));
        }

        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(company.count())
                .from(company);
        return new PageImpl<>(companyReadResponses, pageable, countQuery.fetchCount());
    }

    private CompanyReadResponse tupleToResponse(Tuple result) {
        UUID companyId = result.get(0, UUID.class);
        String name = result.get(1, String.class);
        LocalDateTime createdAt = result.get(2, LocalDateTime.class);
        return new CompanyReadResponse(companyId, name, createdAt);
    }

    private List<OrderSpecifier<?>> getAllOrderSpecifiers() {
        List<OrderSpecifier<?>> orders = new ArrayList<>();
        orders.add(new OrderSpecifier<>(Order.DESC, company.updatedAt));
        return orders;
    }
}