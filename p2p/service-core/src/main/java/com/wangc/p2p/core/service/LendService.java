package com.wangc.p2p.core.service;

import com.wangc.p2p.core.entity.BorrowInfo;
import com.wangc.p2p.core.entity.Lend;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wangc.p2p.core.vo.BorrowInfoApprovalVO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 标的准备表 服务类
 * </p>
 *
 * @author After拂晓
 * @since 2022-05-16
 */
public interface LendService extends IService<Lend> {

    void createLend(BorrowInfoApprovalVO borrowInfoApprovalVO, BorrowInfo borrowInfo);

    List<Lend> selectList();


    Map<String, Object> getLendDetail(Long id);

    BigDecimal getInterestCount(BigDecimal invest, BigDecimal yearRate, Integer totalmonth, Integer returnMethod);

    void makeLoan(Long id);

}
