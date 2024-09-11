package com.lms.eureka.company.infrastructure.repository;

import com.lms.eureka.company.application.dto.CompanyProductReadResponse;
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

import static com.lms.eureka.company.domain.model.QProduct.product;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements QueryDslProductRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<CompanyProductReadResponse> findCompanyProductsBy(UUID companyId, Pageable pageable) {
        List<OrderSpecifier<?>> orders = getAllOrderSpecifiers();

        JPAQuery<Tuple> query = jpaQueryFactory
                .select(
                        product.id,
                        product.name,
                        product.price,
                        product.stock,
                        product.createdAt
                )
                .from(product)
                .where(product.deletedBy.isNotNull())
                .orderBy(orders.toArray(new OrderSpecifier[0]))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<Tuple> results = query.fetch();
        List<CompanyProductReadResponse> companyProductReadResponses = new ArrayList<>();
        for (Tuple result: results) {
            companyProductReadResponses.add(tupleToResponse(result));
        }

        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(product.count())
                .from(product);
        return new PageImpl<>(companyProductReadResponses, pageable, countQuery.fetchCount());
    }

    @Override
    public Page<CompanyProductReadResponse> findCompanyProductsByName(String search, UUID companyId, Pageable pageable) {
        List<OrderSpecifier<?>> orders = getAllOrderSpecifiers();

        JPAQuery<Tuple> query = jpaQueryFactory
                .select(
                        product.id,
                        product.name,
                        product.price,
                        product.stock,
                        product.createdAt,
                        product.updatedAt
                )
                .from(product)
                .where(product.deletedBy.isNotNull())
                .where(product.name.contains(search))
                .orderBy(orders.toArray(new OrderSpecifier[0]))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        List<Tuple> results = query.fetch();
        List<CompanyProductReadResponse> companyProductReadResponses = new ArrayList<>();
        for (Tuple result: results) {
            companyProductReadResponses.add(tupleToResponse(result));
        }

        JPAQuery<Long> countQuery = jpaQueryFactory
                .select(product.count())
                .from(product);
        return new PageImpl<>(companyProductReadResponses, pageable, countQuery.fetchCount());
    }

    private CompanyProductReadResponse tupleToResponse(Tuple result) {
        UUID id = result.get(0, UUID.class);
        String name = result.get(1, String.class);
        Integer price  = result.get(2, Integer.class);
        Integer stock = result.get(3, Integer.class);
        LocalDateTime createdAt = result.get(4, LocalDateTime.class);
        LocalDateTime updatedAt = result.get(5, LocalDateTime.class);
        return new CompanyProductReadResponse(id, name, price, stock, createdAt, updatedAt);
    }

    private List<OrderSpecifier<?>> getAllOrderSpecifiers() {
        List<OrderSpecifier<?>> orders = new ArrayList<>();
        orders.add(new OrderSpecifier<>(Order.DESC, product.updatedAt));
        return orders;
    }
}