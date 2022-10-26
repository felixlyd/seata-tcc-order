package org.example.felixlyd.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/10/26
 */
@FeignClient(name = "tcc-order-storage")
public interface StorageFeginClient {

    @GetMapping("/decrease")
    String decrease(@RequestParam("productId") Long productId,
                    @RequestParam("count") int count);
}
