package org.example.felixlyd.order.tcc.impl;

import com.alibaba.fastjson.JSONObject;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.example.felixlyd.order.bean.Order;
import org.example.felixlyd.order.mapper.OrderMapper;
import org.example.felixlyd.order.tcc.CreateOrderTccAction;
import org.example.felixlyd.order.tcc.util.ResultHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/10/26
 */
@Component
@Slf4j
public class CreateOrderTccActionImpl implements CreateOrderTccAction {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean prepareCreateOrder(BusinessActionContext businessActionContext, Order order) {
        log.info("创建订单：第一阶段，预留资源, xid={}", businessActionContext.getXid());
        orderMapper.createOrder(order);
        log.info("创建订单：第一阶段，完成. xid={}", businessActionContext.getXid());
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean commit(BusinessActionContext businessActionContext) {
        log.info("创建订单：第二阶段，提交订单（订单状态修改为1）, xid={}", businessActionContext.getXid());

        if (ResultHolder.getResult(getClass(), businessActionContext.getXid()) == null) {
            return true;
        }

        Order orderObject = JSONObject.parseObject(businessActionContext.getActionContext("order").toString(), Order.class);
        orderObject.setStatus(1);
        orderMapper.updateOrderStatus(orderObject);
        log.info("创建订单：第二阶段，完成. xid={}", businessActionContext.getXid());
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean rollback(BusinessActionContext businessActionContext) {
        log.info("创建订单：第二阶段，回滚，删除订单, xid={}", businessActionContext.getXid());
        if (ResultHolder.getResult(getClass(), businessActionContext.getXid()) == null) {
            return true;
        }
        Order orderObject = JSONObject.parseObject(businessActionContext.getActionContext("order").toString(), Order.class);

        orderMapper.deleteByOrderId(orderObject);
        log.info("创建订单：第二阶段，完成. xid={}", businessActionContext.getXid());
        return true;
    }
}
