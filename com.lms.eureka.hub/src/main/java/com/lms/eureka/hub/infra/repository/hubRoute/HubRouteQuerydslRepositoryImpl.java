package com.lms.eureka.hub.infra.repository.hubRoute;

import static com.lms.eureka.hub.domain.entity.hubRoute.QHubRoute.hubRoute;

import com.lms.eureka.hub.domain.entity.hubRoute.HubRoute;
import com.lms.eureka.hub.presentation.request.hubRoute.SearchHubRouteRequest;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class HubRouteQuerydslRepositoryImpl implements HubRouteQuerydslRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<HubRoute> searchHubRoute(SearchHubRouteRequest requestParam, Pageable pageable) {
        List<HubRoute> hubRoutes = queryFactory
                .select(hubRoute)
                .from(hubRoute)
                .where(
                        departureHubNameEq(requestParam.departureHubName()),
                        arrivalHubNameEq(requestParam.arrivalHubName())
                )
                .orderBy(hubRoute.createdAt.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(hubRoutes, pageable,  hubRoutes.size());
    }

    private BooleanExpression departureHubNameEq(String departureHubName){
        return departureHubName == null ? null : hubRoute.departureHub.name.eq(departureHubName);
    }

    private BooleanExpression arrivalHubNameEq(String arrivalHubName){
        return arrivalHubName == null ? null : hubRoute.arrivalHub.name.eq(arrivalHubName);
    }

}
