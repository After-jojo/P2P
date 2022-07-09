package com.wangc.p2p.core.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author After拂晓
 * @date 2022年05月18日 10:57
 */
@Data
public class ExcelDictDto {    //数据传输对象
    @ExcelProperty("id")
    private Long id;
    @ExcelProperty("上级id")
    private Long parentId;
    @ExcelProperty("名称")
    private String name;
    @ExcelProperty("值")
    private Integer value;
    @ExcelProperty("编码")
    private String dictCode;
}
