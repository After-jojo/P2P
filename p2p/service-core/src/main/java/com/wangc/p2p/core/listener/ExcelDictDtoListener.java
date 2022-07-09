package com.wangc.p2p.core.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.wangc.p2p.core.dto.ExcelDictDto;
import com.wangc.p2p.core.mapper.DictMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author After拂晓
 * @date 2022年05月18日 11:03
 */
@Slf4j
@NoArgsConstructor
public class ExcelDictDtoListener extends AnalysisEventListener<ExcelDictDto> {
    private DictMapper dictMapper;

    public ExcelDictDtoListener(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }

    //做缓存批量插入
    List<ExcelDictDto> list = new ArrayList<>();
    static final int BATCH_NUM = 5;     //dev: 一次性放入5条 模拟
    @Override
    public void invoke(ExcelDictDto excelDictDto, AnalysisContext analysisContext) {
        log.info("解析到一条记录：{}", excelDictDto);
        list.add(excelDictDto);  //proe: 一次性放入3000条
        if(list.size() >= BATCH_NUM){
            saveData();
            list.clear();
        }
    }
    private void saveData(){
        log.info("{} 条数据存储到数据库", list.size());
        dictMapper.insertBatch(list);
        log.info("{} 条数据存储存储成功", list.size());
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        saveData();  // 存入不足5条的数据
        log.info("全部完成");
    }
}
