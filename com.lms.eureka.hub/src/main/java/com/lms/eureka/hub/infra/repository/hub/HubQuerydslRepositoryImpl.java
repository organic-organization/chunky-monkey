package com.lms.eureka.hub.infra.repository.hub;

import static com.lms.eureka.hub.domain.entity.hub.QHub.hub;

import com.lms.eureka.hub.domain.entity.hub.Hub;
import com.lms.eureka.hub.presentation.request.hub.SearchHubRequest;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class HubQuerydslRepositoryImpl implements HubQuerydslRepository {

    private final JPAQueryFactory queryFactory;

    public Page<Hub> searchHub(SearchHubRequest requestParam, Pageable pageable) {
        List<Hub> hubs = queryFactory
                .select(hub)
                .from(hub)
                .where(
                        hubNameEq(requestParam.hubName()),
                        addressEq(requestParam.address())
                )
                .orderBy(hub.routeIndex.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(hubs, pageable, hubs.size());
    }

    private BooleanExpression hubNameEq(String hubName){
        return hubName == null ? null : hub.name.eq(hubName);
    }

    private BooleanExpression addressEq(String address){
        return address == null ? null : hub.address.eq(address);
    }

}