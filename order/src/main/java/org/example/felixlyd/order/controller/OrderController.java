package org.example.felixlyd.order.controller;

import org.example.felixlyd.order.bean.Order;
import org.example.felixlyd.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/10/26
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/create_order")
    public String createOrder(Order order){
        orderService.create(order);
        return "创建订单成功";
    }
}
