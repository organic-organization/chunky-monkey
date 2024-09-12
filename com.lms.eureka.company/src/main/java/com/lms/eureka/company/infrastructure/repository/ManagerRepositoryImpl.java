package com.lms.eureka.company.infrastructure.repository;

import com.lms.eureka.company.application.dto.CompanyManagerReadResponse;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.lms.eureka.company.domain.model.QCompanyManager.companyManager;

@RequiredArgsConstructor
public class ManagerRepositoryImpl implements QueryDslManagerRepository{

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public Page<CompanyManagerReadResponse> findCompanyManagersBy(Pageable pageable) {
        List<OrderSpecifier<?>> orders = getAllOrderSpecifiers();

        JPAQuery<Tuple> query = jpaQueryFactory
                .select(
                        companyManager.id,
                        companyManager.company.id,
                        companyManager.userId
                )
                .from(companyManager)
                .where(companyManager.deletedBy.isNull())
                .orderBy(orders.toArray(new OrderSpecifier[0]))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<Tuple> results = query.fetch();
        List<CompanyManagerReadResponse> companyManagerReadResponses = new ArrayList<>();
        for (Tuple result: results) {
            companyManagerReadResponses.add(tupleToResponse(result));
        }

        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(companyManager.count())
                .from(companyManager);
        return new PageImpl<>(companyManagerReadResponses, pageable, countQuery.fetchCount());
    }

    private CompanyManagerReadResponse tupleToResponse(Tuple result) {
        UUID id = result.get(0, UUID.class);
        UUID companyId = result.get(1, UUID.class);
        Long userId = result.get(2, Long.class);
        return new CompanyManagerReadResponse(id, companyId, userId);
    }

    private List<OrderSpecifier<?>> getAllOrderSpecifiers() {
        List<OrderSpecifier<?>> orders = new ArrayList<>();
        orders.add(new OrderSpecifier<>(Order.DESC, companyManager.updatedAt));
        return orders;
    }
}
