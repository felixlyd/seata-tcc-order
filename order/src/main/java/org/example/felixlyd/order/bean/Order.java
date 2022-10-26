package org.example.felixlyd.order.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/10/26
 */
@Data
public class Order {
    private Long id;
    private Long orderId;
    private Long userId;
    private Long productId;
    private Integer count;
    private BigDecimal money;
    private Integer status;
}
