package org.example.felixlyd.storage.controller;

import org.example.felixlyd.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/10/25
 */
@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;

    @GetMapping("/decrease")
    public String decrease(Long productId, int count){
        storageService.decrease(productId, count);
        return "扣减库存成功";
    }
}
