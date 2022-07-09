package com.wangc.p2p.core.service;

import com.wangc.p2p.core.entity.UserBind;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wangc.p2p.core.vo.UserBindVO;

import java.util.Map;

/**
 * <p>
 * 用户绑定表 服务类
 * </p>
 *
 * @author After拂晓
 * @since 2022-05-16
 */
public interface UserBindService extends IService<UserBind> {

    String commitBindUser(UserBindVO userBindVO, Long userId);
    void notify(Map<String, Object> paramMap);

    String getBindCodeByUserId(Long userId);

}
