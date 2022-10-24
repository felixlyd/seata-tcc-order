package org.example.felixlyd.account.service;

import java.math.BigDecimal;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/10/24
 */
public interface AccountService {
    void decrease(Long userId, BigDecimal money);
}
