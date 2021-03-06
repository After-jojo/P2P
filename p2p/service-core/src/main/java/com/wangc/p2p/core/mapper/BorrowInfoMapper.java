package com.wangc.p2p.core.mapper;

import com.wangc.p2p.core.entity.BorrowInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 借款信息表 Mapper 接口
 * </p>
 *
 * @author After拂晓
 * @since 2022-05-16
 */
public interface BorrowInfoMapper extends BaseMapper<BorrowInfo> {
    List<BorrowInfo> selectBorrowInfoList();
}
