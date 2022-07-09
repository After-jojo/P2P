package com.wangc.p2p.core.controller.admin;

import com.alibaba.excel.EasyExcel;
import com.wangc.p2p.common.exception.BusinessException;
import com.wangc.p2p.common.result.R;
import com.wangc.p2p.common.result.ResponseEnum;
import com.wangc.p2p.core.dto.ExcelDictDto;
import com.wangc.p2p.core.entity.Dict;
import com.wangc.p2p.core.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author After拂晓
 * @date 2022年05月18日 11:16
 */
@Api(tags = "数据字典管理")
@RestController
@Slf4j
//@CrossOrigin
@RequestMapping("/admin/core/dict")
public class AdminDictController {
    @Resource
    DictService dictService;

    @ApiOperation("Excel数据的批量导入")
    @ApiParam(value = "Excel数据字典文件", required = true)
    @PostMapping("/import")
    public R batchImport(@RequestParam("file") MultipartFile file){
        try {
            dictService.importData(file.getInputStream());
            return R.ok().message("批量导入成功");
        } catch (Exception e) {
            throw new BusinessException(ResponseEnum.UPLOAD_ERROR, e);
        }
    }

    @ApiOperation("Excel数据的导入")
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException{
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String path = URLEncoder.encode("mydict", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + path + ".xlsx");
        EasyExcel.write(response.getOutputStream(), ExcelDictDto.class).sheet("数据字典").doWrite(dictService.listDictData());
    }

    //延迟加载
    @ApiOperation("根据父类别id加载当前类别")
    @ApiParam(value = "父级id", required = true)
    @GetMapping("/listByParentId/{parentId}")
    public R listByParentId(@PathVariable Long parentId){
        List<Dict> dictList = dictService.listByParentId(parentId);
        return R.ok().data("list", dictList);
    }
}
