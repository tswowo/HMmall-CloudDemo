package com.hmall.gateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class MyGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //todo 登录校验
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders requestHeaders = request.getHeaders();
        System.out.println("requestHeaders" + requestHeaders);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
