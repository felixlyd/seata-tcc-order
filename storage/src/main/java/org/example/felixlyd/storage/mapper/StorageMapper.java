package org.example.felixlyd.storage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.felixlyd.storage.bean.Storage;
import org.springframework.stereotype.Component;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/10/25
 */

@Component
public interface StorageMapper extends BaseMapper<Storage> {
    Storage selectByProductId(Long productId);

    int updateResidueAndUsed(Storage storage);

    int updateResidueAndFrozen(Storage storage);

    int updateFrozenAndUsed(Storage storage);
}
