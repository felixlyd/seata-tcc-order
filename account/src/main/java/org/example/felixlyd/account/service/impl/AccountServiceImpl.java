package org.example.felixlyd.account.service.impl;

import org.example.felixlyd.account.service.AccountService;
import org.example.felixlyd.account.tcc.DecreaseAccountTccAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/10/24
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private DecreaseAccountTccAction decreaseAccountTccAction;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        decreaseAccountTccAction.prepareDecrease(null, userId, money);
    }
}
