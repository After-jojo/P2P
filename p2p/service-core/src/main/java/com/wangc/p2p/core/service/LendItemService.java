package com.wangc.p2p.core.service;

import com.wangc.p2p.core.entity.LendItem;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wangc.p2p.core.vo.InvestVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 标的出借记录表 服务类
 * </p>
 *
 * @author After拂晓
 * @since 2022-05-16
 */
public interface LendItemService extends IService<LendItem> {

    String commitInvest(InvestVO investVO);

    void notify(Map<String, Object> paramMap);

    List<LendItem> selectByLendId(Long lendId, Integer status);
    List<LendItem> selectByLendId(Long lendId);
}
