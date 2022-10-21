package org.example.felixlyd.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.felixlyd.account.bean.Account;
import org.springframework.stereotype.Component;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/10/20
 */
@Component
public interface AccountMapper extends BaseMapper<Account> {

    Account selectByUserId(Long userId);
    int updateResidueAndUsed(Account account);

    int updateResidueAndFrozen(Account account);

    int updateFrozenAndUsed(Account account);


}
