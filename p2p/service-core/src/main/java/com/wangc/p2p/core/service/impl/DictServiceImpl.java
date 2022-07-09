package com.wangc.p2p.core.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wangc.p2p.core.dto.ExcelDictDto;
import com.wangc.p2p.core.entity.Dict;
import com.wangc.p2p.core.listener.ExcelDictDtoListener;
import com.wangc.p2p.core.mapper.DictMapper;
import com.wangc.p2p.core.service.DictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author After拂晓
 * @since 2022-05-16
 */
@Slf4j
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {
    @Resource
    private RedisTemplate redisTemplate;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void importData(InputStream inputStream) {
        EasyExcel.read(inputStream, ExcelDictDto.class, new ExcelDictDtoListener(baseMapper)).sheet().doRead();
        log.info("Excel成功");
    }

    @Override
    public List<ExcelDictDto> listDictData() {
        List<Dict> dicts = baseMapper.selectList(null);
        List<ExcelDictDto> list = new ArrayList<>(dicts.size());
//        for (Dict d: dicts) {
//            ExcelDictDto excelDictDto = new ExcelDictDto();
//            BeanUtils.copyProperties(d, excelDictDto);
//            list.add(excelDictDto);
//        }
        dicts.forEach(dict -> {
            ExcelDictDto excelDictDto = new ExcelDictDto();
            BeanUtils.copyProperties(dict, excelDictDto);
            list.add(excelDictDto);
        });
        return list;
    }

    @Override
    public List<Dict> listByParentId(Long parentId) {
        //整合Redis
        try {
            List<Dict> dictList = (List<Dict>) redisTemplate.opsForValue().get("p2p:core:dictList:" + parentId);
            if(dictList != null){
                return dictList;
            }
        } catch (Exception e) {
            log.error("redis异常:" + ExceptionUtils.getStackTrace(e));
        }
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId);
        List<Dict> dicts = baseMapper.selectList(queryWrapper);
        //还要填充hasChildren字段
        dicts.forEach(dict -> {
            boolean b = hasChildren(dict.getId());
            dict.setHasChildren(b);
        });
        try {
            redisTemplate.opsForValue().set("p2p:core:dictList:" + parentId, dicts, 5, TimeUnit.MINUTES);
        } catch (Exception e) {
            log.error("redis异常:" + ExceptionUtils.getStackTrace(e));
        }
        return dicts;
    }

    @Override
    public List<Dict> findByDictCode(String dictCode) {
        QueryWrapper<Dict> dictQueryWrapper = new QueryWrapper<>();
        dictQueryWrapper.eq("dict_code", dictCode);
        Dict dict = baseMapper.selectOne(dictQueryWrapper);
        return this.listByParentId(dict.getId());
    }

    @Override
    public String getNameByParentDictCodeAndValue(String dictCode, Integer value) {
        QueryWrapper<Dict> dictQueryWrapper = new QueryWrapper<>();
        dictQueryWrapper.eq("dict_code", dictCode);
        Dict parentDict = baseMapper.selectOne(dictQueryWrapper);

        if(parentDict == null){
            return "";
        }

        dictQueryWrapper = new QueryWrapper<>();
        dictQueryWrapper
                .eq("parent_id", parentDict.getId())
                .eq("value", value);
        Dict dict = baseMapper.selectOne(dictQueryWrapper);

        if(dict == null){
            return "";
        }

        return dict.getName();
    }

    private boolean hasChildren(Long id){
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", id);
        Integer count = baseMapper.selectCount(queryWrapper);
        return count > 0;
    }
}
