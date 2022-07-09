package com.wangc.p2p.core.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangc.p2p.base.util.JwtUtils;
import com.wangc.p2p.common.exception.Assert;
import com.wangc.p2p.common.result.R;
import com.wangc.p2p.common.result.ResponseEnum;
import com.wangc.p2p.common.util.RegexValidateUtils;
import com.wangc.p2p.core.entity.UserInfo;
import com.wangc.p2p.core.service.UserInfoService;
import com.wangc.p2p.core.vo.LoginVO;
import com.wangc.p2p.core.vo.RegisterVO;
import com.wangc.p2p.core.vo.UserInfoVO;
import com.wangc.p2p.core.vo.query.UserInfoQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户基本信息 前端控制器
 * </p>
 *
 * @author After拂晓
 * @since 2022-05-16
 */
@Api(tags = "会员管理")
@Slf4j
//@CrossOrigin
@RestController
@RequestMapping("/admin/core/userInfo")
public class AdminUserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @ApiOperation("获取会员分页列表")
    @GetMapping("/list/{page}/{limit}")
    public R listPage(
            @ApiParam(value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(value = "查询对象", required = false)
                    UserInfoQuery userInfoQuery){
        Page<UserInfo> pageParam = new Page<>(page, limit);
        IPage<UserInfo> pageModel = userInfoService.listPage(pageParam, userInfoQuery);
        return R.ok().data("pageModel", pageModel);
    }

    @ApiOperation("锁定和解锁")
    @PutMapping("/lock/{id}/{status}")
    public R lock(
            @ApiParam(value = "用户id", required = true)
            @PathVariable("id") Long id,

            @ApiParam(value = "锁定状态（0：锁定 1：正常）", required = true)
            @PathVariable("status") Integer status){

        userInfoService.lock(id, status);
        return R.ok().message(status==1?"解锁成功":"锁定成功");
    }

}







