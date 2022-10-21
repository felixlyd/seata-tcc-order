package org.example.lyd.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.lyd.account.bean.Account;

/**
 * class classname
 *
 * @author : liuyaodong
 * @date 2022/10/20
 */
public interface AccountMapper extends BaseMapper<Account> {

    void decreaseResidueToUsed(Account account);

    void updateResidueToFrozen(Account account);

    void updateFrozenToUsed(Account account);

    void updateFrozenToResidue(Account account);

}
