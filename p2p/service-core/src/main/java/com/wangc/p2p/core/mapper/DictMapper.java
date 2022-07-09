package com.wangc.p2p.core.mapper;

import com.wangc.p2p.core.dto.ExcelDictDto;
import com.wangc.p2p.core.entity.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 数据字典 Mapper 接口
 * </p>
 *
 * @author After拂晓
 * @since 2022-05-16
 */
public interface DictMapper extends BaseMapper<Dict> {
    void insertBatch(List<ExcelDictDto> list);
}
