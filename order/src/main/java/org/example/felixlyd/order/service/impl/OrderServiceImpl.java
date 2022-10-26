package org.example.felixlyd.order.service.impl;

import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.example.felixlyd.order.bean.Order;
import org.example.felixlyd.order.feign.AccountFeginClient;
import org.example.felixlyd.order.feign.IdGeneratorFeginClient;
import org.example.felixlyd.order.feign.StorageFeginClient;
import org.example.felixlyd.order.service.OrderService;
import org.example.felixlyd.order.tcc.CreateOrderTccAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/10/26
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private AccountFeginClient accountFeginClient;

    @Autowired
    private StorageFeginClient storageFeginClient;

    @Autowired
    private IdGeneratorFeginClient idGeneratorFeginClient;

    @Autowired
    private CreateOrderTccAction createOrderTccAction;

    @Override
    @GlobalTransactional
    public void create(Order order) {
        Long orderId = idGeneratorFeginClient.getNextId("tcc_order");
        order.setId(orderId);
        order.setOrderId(orderId);

        String xid = RootContext.getXID();
        log.info("事务开始: " + xid);

        createOrderTccAction.prepareCreateOrder(null, order);

        storageFeginClient.decrease(order.getProductId(), order.getCount());

        accountFeginClient.decrease(order.getUserId(), order.getMoney());
    }
}
