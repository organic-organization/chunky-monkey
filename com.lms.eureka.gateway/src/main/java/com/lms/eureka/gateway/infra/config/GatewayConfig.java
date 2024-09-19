package com.lms.eureka.gateway.infra.config;

import com.lms.eureka.gateway.infra.jwt.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {
    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(
                        "user-service", route -> route.path("/api/{role}/user/**")
                                .filters(filter -> filter.filter((exchange, chain)
                                        ->
                                        jwtAuthorizationFilter.filter(exchange).then(chain.filter(exchange))))
                                .uri("lb://user-service"))
                .route(
                        "hub-service", route -> route.path("/api/{role}/hubs/**", "/api/{role}/hub-routes/**")
                                .filters(filter -> filter.filter((exchange, chain)
                                        ->
                                        jwtAuthorizationFilter.filter(exchange).then(chain.filter(exchange))))
                                .uri("lb://hub-service"))
                .route(
                        "company-service", route -> route.path("/api/{role}/companies/**")
                                .filters(filter -> filter.filter((exchange, chain)
                                        ->
                                        jwtAuthorizationFilter.filter(exchange).then(chain.filter(exchange))))
                                .uri("lb://company-service"))
                .build();
    }

}
