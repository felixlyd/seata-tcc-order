package org.example.felixlyd.idgenerator.controller;

import org.example.felixlyd.idgenerator.service.IdGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/10/18
 */
@RestController
@RequestMapping("/ids")
public class IdController {

    @Autowired
    private IdGeneratorService idGeneratorService;

    @GetMapping("/next_id")
    public Long getNextId(@RequestParam(name = "bizType") String bizType){
        return idGeneratorService.nextId(bizType);
    }

    @GetMapping("/next_id_batch")
    public List<Long> getNextId(@RequestParam(name = "bizType") String bizType, @RequestParam(name = "batchSize") int batchSize){
        return idGeneratorService.nextId(bizType, batchSize);
    }
}
