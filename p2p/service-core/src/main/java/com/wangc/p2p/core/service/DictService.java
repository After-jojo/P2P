package com.wangc.p2p.core.service;

import com.wangc.p2p.core.dto.ExcelDictDto;
import com.wangc.p2p.core.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author After拂晓
 * @since 2022-05-16
 */
public interface DictService extends IService<Dict> {
    void importData(InputStream inputStream);
    List<ExcelDictDto> listDictData();

    List<Dict> listByParentId(Long parentId);

    List<Dict> findByDictCode(String dictCode);

    String getNameByParentDictCodeAndValue(String dictCode, Integer value);
}

