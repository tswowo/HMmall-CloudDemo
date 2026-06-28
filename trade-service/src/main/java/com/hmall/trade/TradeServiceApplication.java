package com.hmall.trade;

import com.hmall.hmapi.client.CartClient;
import com.hmall.hmapi.client.ItemClient;
import com.hmall.hmapi.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("com.hmall.trade.mapper")
@EnableFeignClients(basePackageClasses = {CartClient.class, ItemClient.class},defaultConfiguration = DefaultFeignConfig.class)
@SpringBootApplication
public class TradeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradeServiceApplication.class, args);
    }

}
