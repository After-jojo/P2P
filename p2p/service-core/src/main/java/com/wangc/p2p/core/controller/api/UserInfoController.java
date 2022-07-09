package com.wangc.p2p.core.controller.api;


import com.wangc.p2p.base.util.JwtUtils;
import com.wangc.p2p.common.exception.Assert;
import com.wangc.p2p.common.result.R;
import com.wangc.p2p.common.result.ResponseEnum;
import com.wangc.p2p.common.util.RegexValidateUtils;
import com.wangc.p2p.core.service.UserInfoService;
import com.wangc.p2p.core.vo.LoginVO;
import com.wangc.p2p.core.vo.RegisterVO;
import com.wangc.p2p.core.vo.UserIndexVO;
import com.wangc.p2p.core.vo.UserInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "会员接口")
@Slf4j
//@CrossOrigin
@RestController
@RequestMapping("/api/core/userInfo")
public class UserInfoController {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private UserInfoService userInfoService;

    @ApiOperation("注册")
    @PostMapping("/register")
    public R register(@RequestBody RegisterVO registerVO){
        String mobile = registerVO.getMobile();
        Assert.notEmpty(mobile, ResponseEnum.MOBILE_NULL_ERROR);
        String password = registerVO.getPassword();
        Assert.notEmpty(password, ResponseEnum.PASSWORD_NULL_ERROR);
        String code1 = (String) redisTemplate.opsForValue().get("p2p:sms:code:" + mobile);
        String code = registerVO.getCode();   // 用户输入的验证码
        Assert.notEmpty(code, ResponseEnum.CODE_NULL_ERROR);
        Assert.isTrue(RegexValidateUtils.checkCellphone(mobile), ResponseEnum.MOBILE_ERROR);
        Assert.equals(code, code1, ResponseEnum.CODE_ERROR);
        userInfoService.register(registerVO);
        return R.ok().message("注册成功");
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public R login(@RequestBody LoginVO loginVO, HttpServletRequest httpServletRequest){
        String mobile = loginVO.getMobile();
        String password = loginVO.getPassword();
        Assert.notEmpty(mobile, ResponseEnum.MOBILE_NULL_ERROR);
        Assert.notEmpty(password, ResponseEnum.PASSWORD_NULL_ERROR);
        //填充用户登录日志表
        String ip = httpServletRequest.getRemoteAddr();
        UserInfoVO userInfoVO = userInfoService.login(loginVO, ip);
        return R.ok().data("userInfo", userInfoVO);
    }

    @ApiOperation("校验令牌")
    @GetMapping("/checkToken")
    public R checkToken(HttpServletRequest request) {

        String token = request.getHeader("token");
        boolean result = JwtUtils.checkToken(token);
        if(result){
            return R.ok();
        }else{
            return R.setResult(ResponseEnum.LOGIN_AUTH_ERROR);
        }

    }

    @ApiOperation("校验手机号是否注册")
    @GetMapping("/checkMobile/{mobile}")
    public boolean checkMobile(@PathVariable String mobile){
        return userInfoService.checkMobile(mobile);
    }
    @ApiOperation("获取个人主页用户信息")
    @GetMapping("/auth/getIndexUserInfo")
    public R getIndexUserInfo(HttpServletRequest request){
        String token = request.getHeader("token");
        Long userId = JwtUtils.getUserId(token);
        UserIndexVO userIndexVO = userInfoService.getIndexUserInfo(userId);
        return R.ok().data("userIndexVO", userIndexVO);
    }
}







