package com.wangc.p2p.core.service;

import com.wangc.p2p.core.entity.TransFlow;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wangc.p2p.core.vo.TransFlowBO;

import java.util.List;

/**
 * <p>
 * 交易流水表 服务类
 * </p>
 *
 * @author After拂晓
 * @since 2022-05-16
 */
public interface TransFlowService extends IService<TransFlow> {
    void saveTransFlow(TransFlowBO transFlowBO);

    boolean isSaveTransFlow(String agentBillNo);

    List<TransFlow> selectByUserId(Long userId);

}
