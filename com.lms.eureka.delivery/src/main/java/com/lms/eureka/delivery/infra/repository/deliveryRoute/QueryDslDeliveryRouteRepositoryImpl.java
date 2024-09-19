package com.lms.eureka.delivery.infra.repository.deliveryRoute;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import static com.lms.eureka.delivery.domain.entity.deliveryRoute.QDeliveryRoute.deliveryRoute;


@Slf4j
@Repository
@RequiredArgsConstructor
public class QueryDslDeliveryRouteRepositoryImpl implements QueryDslDeliveryRouteRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public UUID findDeliveryManagerWhereMinCnt(UUID startHubId) {
       UUID managerId = jpaQueryFactory
               .select(deliveryRoute.deliveryManagerId)
               .from(deliveryRoute)
               .where(deliveryRoute.startHubId.eq(startHubId))
               .groupBy(deliveryRoute.deliveryManagerId)
               .orderBy(deliveryRoute.deliveryManagerId.count().asc()) // COUNT() 기준으로 오름차순 정렬
               .fetchFirst();

        return managerId;
    }
}