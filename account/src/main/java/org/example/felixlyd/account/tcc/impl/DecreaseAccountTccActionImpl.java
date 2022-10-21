package org.example.felixlyd.account.tcc.impl;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import lombok.extern.slf4j.Slf4j;
import org.example.felixlyd.account.bean.Account;
import org.example.felixlyd.account.mapper.AccountMapper;
import org.example.felixlyd.account.tcc.DecreaseAccountTccAction;
import org.example.felixlyd.account.tcc.util.ResultHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/10/21
 */
@Slf4j
@Component
public class DecreaseAccountTccActionImpl implements DecreaseAccountTccAction {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean prepareDecrease(BusinessActionContext businessActionContext, Long userId, BigDecimal money) {
        log.info("扣减账户金额：第一阶段，锁定金额，userId={}, money={}, xid={}.", userId, money, businessActionContext.getXid());

        // 查询账户
        Account account = accountMapper.selectByUserId(userId);

        // 判断账户余额是否充足
        if(account.getResidue().compareTo(money) < 0){
            throw new RuntimeException("账户余额不足");
        }

        // 锁定余额到冻结额
        account.setResidue(account.getResidue().subtract(money));
        account.setFrozen(account.getFrozen().add(money));

        // 更新账户的余额、冻结额
        accountMapper.updateResidueAndFrozen(account);

        // 保存上下文标识
        ResultHolder.setResult(getClass(), businessActionContext.getXid(), "prepare");

        log.info("扣减账户金额：第一阶段，完成. xid={}", businessActionContext.getXid());
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean commit(BusinessActionContext businessActionContext) {
        Long userId = Long.parseLong(businessActionContext.getActionContext("userId").toString());
        BigDecimal money = new BigDecimal(businessActionContext.getActionContext("money").toString());
        log.info("扣减账户金额：第二阶段，提交，userId={}, money={}, xid={}.", userId, money, businessActionContext.getXid());

        // 查询上下文标识发现为null时，说明try方法还未执行或者该上下文已被处理
        if(ResultHolder.getResult(getClass(), businessActionContext.getXid())==null){
            return true;
        }

        // 查询账户
        Account account = accountMapper.selectByUserId(userId);

        // 更新账户的冻结额、已使用
        account.setFrozen(account.getFrozen().subtract(money));
        account.setUsed(account.getUsed().add(money));

        // 更新账户的冻结额、已用额
        accountMapper.updateFrozenAndUsed(account);

        // 删除上下文标识
        ResultHolder.removeResult(getClass(), businessActionContext.getXid());

        log.info("扣减账户金额：第二阶段，提交完成. xid={}", businessActionContext.getXid());
        return true;
    }

    @Override
    @Transactional
    public boolean rollback(BusinessActionContext businessActionContext) {
        return false;
    }
}
