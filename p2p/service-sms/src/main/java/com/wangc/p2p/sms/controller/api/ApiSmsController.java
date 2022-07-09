package com.wangc.p2p.sms.controller.api;

import com.wangc.p2p.common.exception.Assert;
import com.wangc.p2p.common.result.R;
import com.wangc.p2p.common.result.ResponseEnum;
import com.wangc.p2p.common.util.RandomUtils;
import com.wangc.p2p.common.util.RegexValidateUtils;
import com.wangc.p2p.sms.client.CoreUserInfoClient;
import com.wangc.p2p.sms.service.SmsService;
import com.wangc.p2p.sms.util.SmsProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author After拂晓
 * @date 2022年05月20日 19:18
 */

//@CrossOrigin
@RestController
@Api(tags = "短信管理")
@RequestMapping("/api/sms")
public class ApiSmsController {
    @Resource
    private SmsService smsService;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private CoreUserInfoClient coreUserInfoClient;

    @ApiOperation("获取验证码")
    @GetMapping("/send/{mobile}")
    @ApiParam(value = "手机号", required = true)
    public R send(@PathVariable String mobile){
        //验证手机号码不为空
        Assert.notEmpty(mobile, ResponseEnum.MOBILE_NULL_ERROR);
        //验证手机号是否合法
        Assert.isTrue(RegexValidateUtils.checkCellphone(mobile), ResponseEnum.MOBILE_ERROR);
        //判断手机号是否已经注册
        boolean b = coreUserInfoClient.checkMobile(mobile);
        Assert.isTrue(b == false, ResponseEnum.MOBILE_EXIST_ERROR);
        HashMap<String, Object> map = new HashMap<>();
        String code = RandomUtils.getFourBitRandom();
        map.put("code", code);
//        smsService.send(mobile, SmsProperties.TEMPLATE_CODE, map);

        //将验证码存入Redis
        redisTemplate.opsForValue().set("p2p:sms:code:" + mobile, code, 5, TimeUnit.MINUTES);
        return R.ok().message("短信发送成功");
    }
}
