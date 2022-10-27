use `tcc-order`;

CREATE TABLE `order` (
                         `id` bigint(11) NOT NULL,
                         `order_id` bigint(11) NOT NULL COMMENT '订单id',
                         `user_id` bigint(11) DEFAULT NULL COMMENT '用户id',
                         `product_id` bigint(11) DEFAULT NULL COMMENT '产品id',
                         `count` int(11) DEFAULT NULL COMMENT '数量',
                         `money` decimal(11,0) DEFAULT NULL COMMENT '金额',
                         `status` int(1) DEFAULT NULL COMMENT '订单状态：0：创建中；1：已完结',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

# ALTER TABLE `order` ADD COLUMN `status` int(1) DEFAULT NULL COMMENT '订单状态：0：创建中；1：已完结' AFTER `money` ;