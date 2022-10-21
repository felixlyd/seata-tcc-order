package org.example.felixlyd.account.tcc;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import org.example.felixlyd.account.bean.Account;

import java.math.BigDecimal;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/10/21
 */

@LocalTCC
public interface DecreaseAccountTccAction {

    /**
     * 扣减账户金额：第一阶段
     * @param businessActionContext seata事务上下文
     * @param userId 用户id
     * @param money 钱
     * @return 是否完成
     */
    @TwoPhaseBusinessAction(name = "decreaseAccountTccAction")
    boolean prepareDecrease(BusinessActionContext businessActionContext,
                            @BusinessActionContextParameter(paramName = "userId") Long userId,
                            @BusinessActionContextParameter(paramName = "money") BigDecimal money);

    /**
     * 扣减账户余额：第二阶段，提交
     * Confirm一定要在 Try 之后执行
     * @param businessActionContext
     * @return
     */
    boolean commit(BusinessActionContext businessActionContext);

    boolean rollback(BusinessActionContext businessActionContext);
}
