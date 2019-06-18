package com.michaelia.emma.web.sys;

import com.michaelia.emma.common.Response;
import com.michaelia.emma.common.ResponseUtil;
import com.michaelia.emma.common.utils.ShiroUtil;
import com.michaelia.emma.entity.sys.SysRole;
import com.michaelia.emma.enums.GlobleEnum;
import com.michaelia.emma.service.sys.BaseSysRoleService;
import com.michaelia.emma.service.sys.BaseSysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 方法名:          shiroController
 * 方法功能描述:
 *
 * @param:
 * @return:
 * @Author: 陈超
 * @Create Date:   2019/6/11
 */
@RestController
@Api(value = "登录认证", description = "登录认证")
public class ShiroController {

    @Autowired
    private BaseSysRoleService sysRoleService;

    @Autowired
    private BaseSysUserService userService;

    @PostMapping(value = "/login")
    @ApiOperation(value = "登录")
    public Response<Map<String, Object>> login(@RequestBody Map<String, String> map) {
        String jobNo = map.get("jobNo");
        String password = map.get("password");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(jobNo, password);
        try {
            subject.login(token);
        } catch (IncorrectCredentialsException e) {
            return ResponseUtil.fail(GlobleEnum.SYS_INCORRECT_CREDENTIALS.getCode(), GlobleEnum.SYS_INCORRECT_CREDENTIALS.getMessage());
        } catch (UnknownAccountException e) {
            return ResponseUtil.fail(GlobleEnum.SYS_UNKNOWN_ACCOUNT.getCode(), GlobleEnum.SYS_UNKNOWN_ACCOUNT.getMessage());
        } catch (LockedAccountException e) {
            return ResponseUtil.fail(GlobleEnum.SYS_USER_LOCKED.getCode(), GlobleEnum.SYS_USER_LOCKED.getMessage());
        } catch (Exception e) {
            return ResponseUtil.fail(GlobleEnum.SYS_SYSTEM_ERROR.getCode(), GlobleEnum.SYS_SYSTEM_ERROR.getMessage());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("defaultPassword", ShiroUtil.getUser().getDefaultPassword());
        result.put("sessionId", subject.getSession().getId());
        result.put("userId", String.valueOf(ShiroUtil.getUserId()));

        List<SysRole> roles = sysRoleService.getRoleByUser(ShiroUtil.getUserId());
        result.put("roles", CollectionUtils.isNotEmpty(roles) ? roles.stream().map(SysRole::getRoleCode).collect(Collectors.toSet()) : null);
        return ResponseUtil.success(result);
    }
}
