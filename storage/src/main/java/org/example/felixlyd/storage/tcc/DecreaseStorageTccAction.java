package org.example.felixlyd.storage.tcc;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/10/25
 */
@LocalTCC
public interface DecreaseStorageTccAction {

    @TwoPhaseBusinessAction(name = "decreaseStorageTccAction")
    boolean prepareDecrease(BusinessActionContext businessActionContext,
                            @BusinessActionContextParameter(paramName = "productId") Long productId,
                            @BusinessActionContextParameter(paramName = "count") int count);

    boolean commit(BusinessActionContext businessActionContext);

    boolean rollback(BusinessActionContext businessActionContext);
}
