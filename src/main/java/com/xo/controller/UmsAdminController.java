package com.xo.controller;

import com.xo.mbg.param.UmsAdminLoginParam;
import com.xo.mbg.model.UmsAdmin;
import com.xo.mbg.model.UmsPermission;
import com.xo.service.SmsService;
import com.xo.service.UmsAdminService;
import com.xo.utils.LoginUserUtil;
import com.xo.utils.api.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台用户管理
 * Created by macro on 2018/4/26.
 */
@Controller
@Api(tags = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {

    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private SmsService smsService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation(value = "后台用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<UmsAdmin> register(@RequestBody UmsAdmin umsAdminParam, BindingResult result) {
        UmsAdmin umsAdmin = adminService.register(umsAdminParam);
        if (umsAdmin == null) {
            CommonResult.requestError("error");
        }
        return CommonResult.success("success",umsAdmin);
    }

    @ApiOperation(value = "后台账号密码登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<?> login(@RequestBody UmsAdminLoginParam umsAdminLoginParam, BindingResult result) {
        String token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            return CommonResult.requestError("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success("success",tokenMap);
    }

    @ApiOperation("后台获取验证码")
    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAuthCode(@RequestParam String telephone) {
        if(!LoginUserUtil.checkTelephone(telephone)){
            return CommonResult.requestError("手机格式错误");
        }
        return smsService.generateAuthCode(telephone);
    }

    @ApiOperation(value = "后台手机号登录返回token")
    @RequestMapping(value = "/loginByPhone", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<?> loginByPhone(@RequestParam String telephone, @RequestParam String authCode){
        if(!LoginUserUtil.checkTelephone(telephone)){
            return CommonResult.requestError("手机格式错误");
        }
        if(StringUtils.isEmpty(authCode)){
            return CommonResult.requestError("验证码为空");
        }
        String token = adminService.loginByPhone(telephone,authCode);
        if (token == null) {
            return CommonResult.requestError("验证码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success("success",tokenMap);
    }

    @ApiOperation("获取用户所有权限（包括+-权限）")
    @RequestMapping(value = "/permission/{adminId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsPermission>> getPermissionList(@PathVariable Long adminId) {
        List<UmsPermission> permissionList = adminService.getPermissionList(adminId);
        return CommonResult.success("success",permissionList);
    }



}
