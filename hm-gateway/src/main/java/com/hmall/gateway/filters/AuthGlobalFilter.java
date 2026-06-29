package com.hmall.gateway.filters;

import com.hmall.common.utils.UserContext;
import com.hmall.gateway.config.AuthProperties;
import com.hmall.gateway.utils.JwtTool;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private final AuthProperties authProperties;
    private final JwtTool jwtTool;
    private final AntPathMatcher matcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        //校验是否拦截
        if (this.isExcludePath(request.getPath().value())) {
            return chain.filter(exchange);
        }
        String token = null;
        List<String> tokens = request.getHeaders().get("Authorization");
        if (tokens != null && !tokens.isEmpty()) {
            token = tokens.get(0);
        }
        //尝试解析token
        Long userId = null;
        try {
            userId = jwtTool.parseToken(token);
        } catch (Exception e) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        //传递用户信息给下游
        String userInfo = userId.toString();
        exchange.mutate()
                .request(
                        builder -> builder.header(UserContext.USER_HEADER, userInfo)
                                .build()
                );
        return chain.filter(exchange);
    }

    private boolean isExcludePath(String value) {
        List<String> excludePaths = authProperties.getExcludePaths();
        for (String excludePath : excludePaths) {
            if (matcher.match(excludePath, value))
                return true;
        }
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
