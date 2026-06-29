package com.hmall.gateway.filters;

import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class MyGatewayFilterFactory extends AbstractGatewayFilterFactory<MyGatewayFilterFactory.Config> {

    public MyGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new OrderedGatewayFilter(new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                System.out.println("MyGatewayFilterFactory已经执行");
                System.out.println("a:" + config.getA());
                System.out.println("b:" + config.getB());
                System.out.println("c:" + config.getC());
                return chain.filter(exchange);
            }
        }, 1);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return List.of("a", "b", "c");
    }

    @Data
    public static class Config {
        private String a;
        private String b;
        private String c;
    }
}
