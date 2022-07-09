package com.wangc.p2p.core.mapper;

import com.wangc.p2p.core.entity.UserAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * <p>
 * 用户账户 Mapper 接口
 * </p>
 *
 * @author After拂晓
 * @since 2022-05-16
 */
public interface UserAccountMapper extends BaseMapper<UserAccount> {

    void updateAccount( @Param("bindCode")String bindCode,
                       @Param("amount") BigDecimal amount,
                       @Param("freezeAmount")BigDecimal freezeAmount);

}
