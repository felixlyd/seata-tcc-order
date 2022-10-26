package org.example.felixlyd.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/10/26
 */
@FeignClient(name = "tcc-order-account")
public interface AccountFeginClient {

    @GetMapping("/decrease")
    String decrease(@RequestParam(name = "userId") Long userId,
                    @RequestParam(name = "money") BigDecimal money);

}
