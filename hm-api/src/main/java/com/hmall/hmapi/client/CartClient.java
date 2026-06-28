package com.hmall.hmapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "cart-service")
public interface CartClient {
    @GetMapping("/carts")
    void deleteCartItemByIds(@RequestParam("ids") List<Long> ids);
}
