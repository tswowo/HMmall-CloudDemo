package com.hmall.pay;

import com.hmall.hmapi.client.OrderClient;
import com.hmall.hmapi.client.UserClient;
import com.hmall.hmapi.config.DefaultFeignConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("com.hmall.pay.mapper")
@SpringBootApplication
@EnableFeignClients(basePackageClasses = {OrderClient.class, UserClient.class}, defaultConfiguration = DefaultFeignConfig.class)
public class PayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PayServiceApplication.class, args);
    }

}
