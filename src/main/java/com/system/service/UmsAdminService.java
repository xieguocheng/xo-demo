package com.system.service;


import com.system.mbg.model.UmsAdmin;
import com.system.mbg.model.UmsPermission;

import java.util.List;

/**
 * 后台管理员Service
 * Created by macro on 2018/4/26.
 */
public interface UmsAdminService {
    /**
     * 根据用户名获取后台管理员
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 根据手机号获取后台管理员
     * @param phone
     * @return
     */
    UmsAdmin getAdminByPhone(String phone);

    /**
     * 注册功能
     */
    UmsAdmin register(UmsAdmin umsAdminParam);

    /**
     * 用户名密码登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    /**
     * 验证码登录
     * @param telephone
     * @param authCode
     * @return
     */
    String loginByPhone(String telephone, String authCode);

    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     */
    List<UmsPermission> getPermissionList(Long adminId);
}
