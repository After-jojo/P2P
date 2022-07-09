package com.wangc.p2p.core.service;

import com.wangc.p2p.core.entity.BorrowInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wangc.p2p.core.vo.BorrowInfoApprovalVO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 借款信息表 服务类
 * </p>
 *
 * @author After拂晓
 * @since 2022-05-16
 */
public interface BorrowInfoService extends IService<BorrowInfo> {

    BigDecimal getBorrowAmount(Long userId);

    void saveBorrowInfo(BorrowInfo borrowInfo, Long userId);

    Integer getStatusByUserId(Long userId);

    List<BorrowInfo> selectList();

    Map<String, Object> getBorrowInfoDetail(Long id);

    void approval(BorrowInfoApprovalVO borrowInfoApprovalVO);

}
