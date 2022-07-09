package com.wangc.p2p.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangc.p2p.core.entity.Borrower;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wangc.p2p.core.vo.BorrowerApprovalVO;
import com.wangc.p2p.core.vo.BorrowerDetailVO;
import com.wangc.p2p.core.vo.BorrowerVO;

/**
 * <p>
 * 借款人 服务类
 * </p>
 *
 * @author After拂晓
 * @since 2022-05-16
 */
public interface BorrowerService extends IService<Borrower> {

    void saveBorrowerVOByUserId(BorrowerVO borrowerVO, Long userId);

    Integer getStatusByUserId(Long userId);

    IPage<Borrower> listPage(Page<Borrower> pageParam, String keyword);

    BorrowerDetailVO getBorrowerDetailVOById(Long id);

    void approval(BorrowerApprovalVO borrowerApprovalVO);

}
