package com.wangc.p2p.core.service;

import com.wangc.p2p.core.entity.BorrowerAttach;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wangc.p2p.core.vo.BorrowerAttachVO;

import java.util.List;

/**
 * <p>
 * 借款人上传资源表 服务类
 * </p>
 *
 * @author After拂晓
 * @since 2022-05-16
 */
public interface BorrowerAttachService extends IService<BorrowerAttach> {

    List<BorrowerAttachVO> selectBorrowerAttachVOList(Long id);
}
