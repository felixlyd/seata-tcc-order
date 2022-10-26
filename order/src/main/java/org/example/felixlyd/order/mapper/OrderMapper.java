package org.example.felixlyd.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.felixlyd.order.bean.Order;
import org.springframework.stereotype.Component;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/10/26
 */
@Component
public interface OrderMapper extends BaseMapper<Order> {
    void createOrder(Order order);

    int updateOrderStatus(Order order);

    void deleteByOrderId(Order order);
}
