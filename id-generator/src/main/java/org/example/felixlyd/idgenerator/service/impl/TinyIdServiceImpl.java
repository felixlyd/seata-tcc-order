package org.example.felixlyd.idgenerator.service.impl;

import com.xiaoju.uemc.tinyid.client.utils.TinyId;
import org.example.felixlyd.idgenerator.service.IdGeneratorService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/10/18
 */
@Service(value = "tinyIdService")
public class TinyIdServiceImpl implements IdGeneratorService {
    @Override
    public Long nextId(String bizType) {
        return TinyId.nextId(bizType);
    }

    @Override
    public List<Long> nextId(String bizType, int batchSize) {
        return TinyId.nextId(bizType, batchSize);
    }
}
