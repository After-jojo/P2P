package com.wangc.p2p.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangc.p2p.core.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wangc.p2p.core.vo.LoginVO;
import com.wangc.p2p.core.vo.RegisterVO;
import com.wangc.p2p.core.vo.UserIndexVO;
import com.wangc.p2p.core.vo.UserInfoVO;
import com.wangc.p2p.core.vo.query.UserInfoQuery;

/**
 * <p>
 * 用户基本信息 服务类
 * </p>
 *
 * @author After拂晓
 * @since 2022-05-16
 */
public interface UserInfoService extends IService<UserInfo> {

    void register(RegisterVO registerVO);
    UserInfoVO login(LoginVO loginVO, String ip);
    IPage<UserInfo> listPage(Page<UserInfo> pageParam, UserInfoQuery userInfoQuery);
    void lock(Long id, Integer status);

    boolean checkMobile(String mobile);

    UserIndexVO getIndexUserInfo(Long userId);

}
