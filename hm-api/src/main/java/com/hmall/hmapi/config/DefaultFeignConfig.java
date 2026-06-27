package com.hmall.hmapi.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class DefaultFeignConfig {
    @Bean
    public Logger.Level defaultFeignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
