package com.wangc.p2p.core.controller.admin;


import com.wangc.p2p.common.exception.Assert;
import com.wangc.p2p.common.result.R;
import com.wangc.p2p.common.result.ResponseEnum;
import com.wangc.p2p.core.entity.IntegralGrade;
import com.wangc.p2p.core.service.IntegralGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 积分等级表 前端控制器
 * </p>
 *
 * @author After拂晓
 * @since 2022-05-16
 */
@Api(tags = "积分等级管理")
//@CrossOrigin
@RestController
@RequestMapping("/admin/core/integralGrade")
public class AdminIntegralGradeController {
    @Resource
    private IntegralGradeService integralGradeService;

    @ApiOperation("查询积分等级")
    @GetMapping("/list")
    public R listAll(){
        List<IntegralGrade> list = integralGradeService.list();
        return R.ok().data("list", list);
    }

    @ApiOperation(value = "根据ID删除积分等级记录", notes = "逻辑删除")
    @DeleteMapping("/remove/{id}")
    @ApiParam(value = "id", example = "1", required = true)
    public R removeById(@PathVariable Long id){
        boolean b = integralGradeService.removeById(id);
        if(b){
            return R.ok().message("删除成功");
        }
        return R.error().message("删除失败");
    }

    @ApiOperation("新增积分等级")
    @PostMapping("/save")
    @ApiParam(value = "积分等级对象", required = true)
    public R save(@RequestBody IntegralGrade integralGrade){
//        if(integralGrade.getBorrowAmount() == null){
//            throw new BusinessException(ResponseEnum.BORROW_AMOUNT_NULL_ERROR);
//        }
        //使用断言解决
        Assert.notNull(integralGrade.getBorrowAmount(), ResponseEnum.BORROW_AMOUNT_NULL_ERROR);
        boolean save = integralGradeService.save(integralGrade);
        if(!save){
            return R.error().message("保存失败");
        }
        return R.ok().message("保存成功");
    }

    @ApiOperation("根据id获取积分等级")
    @ApiParam(value = "id", required = true)
    @GetMapping("/get/{id}")
    public R getById(@PathVariable Long id){
        IntegralGrade res = integralGradeService.getById(id);
        if(res == null){
            return R.error().message("没有该记录");
        }
        return R.ok().data("record", res);
    }

    @ApiOperation("修改积分等级")
    @PutMapping("/update")
    @ApiParam(value = "积分等级对象", required = true)
    public R updateById(@RequestBody IntegralGrade integralGrade){
        boolean res = integralGradeService.updateById(integralGrade);
        if(!res){
            return R.error().message("修改失败");
        }
        return R.ok().message("修改成功");
    }
}

