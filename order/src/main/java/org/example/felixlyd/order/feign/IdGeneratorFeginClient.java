package org.example.felixlyd.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/10/26
 */
@FeignClient(name = "tcc-order-id-generator")
@RequestMapping("/ids")
public interface IdGeneratorFeginClient {

    @GetMapping("/next_id")
    Long getNextId(@RequestParam(name = "bizType") String bizType);

    @GetMapping("/next_id_batch")
    List<Long> getNextId(@RequestParam(name = "bizType") String bizType,
                         @RequestParam(name = "batchSize") int batchSize);
}
