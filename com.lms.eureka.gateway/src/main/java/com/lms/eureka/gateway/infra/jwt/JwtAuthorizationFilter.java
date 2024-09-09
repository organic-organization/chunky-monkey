package com.lms.eureka.gateway.infra.jwt;

import com.lms.eureka.gateway.domain.model.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter{

    private final JwtUtil jwtUtil;

    public Mono<Void> filter(ServerWebExchange exchange) {

        String[] pathSegments = exchange.getRequest().getURI().getPath().split("/");
        String roleInPath = pathSegments[2];

        // 인증 필요없는 api
        if(roleInPath.equals("public")){
            return Mono.empty();
        } else {

            String token = extractToken(exchange.getRequest().getHeaders().getFirst("Authorization"));

            // token 없는 경우
            if (token == null) {
                return response(exchange, HttpStatus.FORBIDDEN);
            }

            // 인증 필요 API
            if (jwtUtil.validateToken(token)) {
                UserRoleEnum role = jwtUtil.getRole(token);

                // 잘못된 jwt
                if (role == null) {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }

                // 권한 없음
                if (!isAuthorized(roleInPath, role)) {
                    return response(exchange, HttpStatus.UNAUTHORIZED);
                }

                // 헤더에 username 추가
                exchange.getRequest().mutate()
                        .header("username", jwtUtil.getUsername(token))
                        .build();

                return Mono.empty();
            } else {
                return response(exchange, HttpStatus.UNAUTHORIZED);
            }
        }
    }

    private String extractToken(String authorization) {
        if (authorization != null && authorization.startsWith("Bearer ")) {
            return authorization.substring(7);
        }
        return null;
    }

    private boolean isAuthorized(String requestPath, UserRoleEnum role) {
        switch (role) {
            case MASTER -> {
                return true;
            }
            case HUB_MANAGER -> {
                return !requestPath.equals("master");
            }
            case DELIVERY_AGENT -> {
                return !requestPath.equals("master") && !requestPath.equals("hub-manager");
            }
            case COMPANY_MANAGER -> {
                return !requestPath.equals("master") && !requestPath.equals("hub-manager") && !requestPath.equals("delivery-agent");
            }
            default -> {
                return false;
            }
        }
    }

    private Mono<Void> response(ServerWebExchange exchange, HttpStatus status) {
        exchange.getResponse().setStatusCode(status);
        return exchange.getResponse().setComplete();
    }
}
