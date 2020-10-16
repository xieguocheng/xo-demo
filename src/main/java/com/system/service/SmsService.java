package com.system.service;

import com.system.utils.api.CommonResult;

/**
 * @Author： XO
 * @Description：
 * @Date： 2020/5/8 16:05
 */

public interface SmsService {

    String REDIS_KEY_PREFIX_AUTH_CODE="sms:login:";

    int AUTH_CODE_EXPIRE_SECONDS=120;

    /**
     * 生成验证码
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * 判断验证码和手机号码是否匹配
     */
    CommonResult verifyAuthCode(String telephone, String authCode);



}
