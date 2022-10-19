package org.example.felixlyd.idgenerator.service.impl;

import org.example.felixlyd.idgenerator.aop.CustomConditionalOnProperty;
import org.example.felixlyd.idgenerator.service.IdGeneratorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/10/19
 */
@Service
@CustomConditionalOnProperty(name = "id-generator.type", value = "test")
public class TestIdServiceImpl implements IdGeneratorService {

    @Override
    public Long nextId(String bizType) {
        return 1L;
    }

    @Override
    public List<Long> nextId(String bizType, int batchSize) {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        return ids;
    }
}
