package org.example.felixlyd.storage.tcc.impl;

import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.example.felixlyd.storage.bean.Storage;
import org.example.felixlyd.storage.mapper.StorageMapper;
import org.example.felixlyd.storage.tcc.DecreaseStorageTccAction;
import org.example.felixlyd.storage.tcc.util.ResultHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/10/25
 */
@Slf4j
@Component
public class DeacreaseStorageTccActionImpl implements DecreaseStorageTccAction {

    @Autowired
    private StorageMapper storageMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean prepareDecrease(BusinessActionContext businessActionContext, Long productId, int count) {
        log.info("扣减库存数目：第一阶段，锁定库存，productId={}, count={}, xid={}.", productId, count, businessActionContext.getXid());
        Storage storage = storageMapper.selectByProductId(productId);

        if(storage.getResidue().compareTo(count)<0){
            throw new RuntimeException("库存不足");
        }

        storage.setFrozen(storage.getFrozen()+count);
        storage.setResidue(storage.getResidue()-count);

        storageMapper.updateResidueAndFrozen(storage);

        ResultHolder.setResult(getClass(), businessActionContext.getXid(), "prepare");

        log.info("扣减库存数目：第一阶段，完成. xid={}", businessActionContext.getXid());
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean commit(BusinessActionContext businessActionContext) {
        Long productId = Long.parseLong(businessActionContext.getActionContext("productId").toString());
        int count = Integer.parseInt(businessActionContext.getActionContext("count").toString());
        log.info("扣减库存数目：第二阶段，提交，productId={}, count={}, xid={}.", productId, count, businessActionContext.getXid());

        // 查询上下文标识发现为null时，说明try方法还未执行或者该上下文已被处理
        if(ResultHolder.getResult(getClass(), businessActionContext.getXid())==null){
            return true;
        }

        Storage storage = storageMapper.selectByProductId(productId);

        storage.setFrozen(storage.getFrozen()-count);
        storage.setUsed(storage.getUsed()+count);

        storageMapper.updateFrozenAndUsed(storage);

        log.info("扣减库存数目：第二阶段，提交完成. xid={}", businessActionContext.getXid());
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean rollback(BusinessActionContext businessActionContext) {
        Long productId = Long.parseLong(businessActionContext.getActionContext("productId").toString());
        int count = Integer.parseInt(businessActionContext.getActionContext("count").toString());
        log.info("扣减库存数目：第二阶段，回滚，productId={}, count={}, xid={}.", productId, count, businessActionContext.getXid());

        // 查询上下文标识发现为null时，说明try方法还未执行或者该上下文已被处理
        if(ResultHolder.getResult(getClass(), businessActionContext.getXid())==null){
            return true;
        }

        Storage storage = storageMapper.selectByProductId(productId);

        storage.setFrozen(storage.getFrozen()-count);
        storage.setResidue(storage.getResidue()+count);

        storageMapper.updateResidueAndFrozen(storage);

        log.info("扣减库存数目：第二阶段，回滚完成. xid={}", businessActionContext.getXid());
        return true;
    }
}
