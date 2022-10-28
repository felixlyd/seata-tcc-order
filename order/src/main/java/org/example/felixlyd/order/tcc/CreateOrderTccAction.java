package org.example.felixlyd.order.tcc;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;
import org.example.felixlyd.order.bean.Order;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/10/26
 */
@LocalTCC
public interface CreateOrderTccAction {

    @TwoPhaseBusinessAction(name="createOrderTccAction", commitMethod = "commit", rollbackMethod = "rollback")
    boolean prepareCreateOrder(BusinessActionContext businessActionContext,
                               @BusinessActionContextParameter(paramName = "order") Order order);

    boolean commit(BusinessActionContext businessActionContext);

    boolean rollback(BusinessActionContext businessActionContext);
}
