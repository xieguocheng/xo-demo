package com.xo.controller;

import com.xo.web.service.SmsService;
import com.xo.utils.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author： XO
 * @Description：
 * @Date： 2020/5/8 16:12
 */
@RestController
@RequestMapping("/user")
@Api(tags = "短信验证")
public class SmsController {

    @Autowired
    private SmsService smsService;



    @ApiOperation("获取验证码")
    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAuthCode(@RequestParam String telephone) {
        return smsService.generateAuthCode(telephone);
    }

    @ApiOperation("判断验证码是否正确")
    @RequestMapping(value = "/verifyAuthCode", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePassword(@RequestParam String telephone,
                                       @RequestParam String authCode) {
        return smsService.verifyAuthCode(telephone,authCode);
    }



}
