package com.michaelia.emma.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.List;

/**
 * 
 * @author fengyouwei
 *
 */
public class UserRealm extends AuthorizingRealm {
   /* @Autowired
    @Lazy
    private SysRoleService sysRoleService;
    @Autowired
    @Lazy
    private SysMenuService sysMenuService;
    @Autowired
    @Lazy
    private SysUserService sysUserService;*/

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        /*SysUser sysUser = (SysUser) principals.getPrimaryPrincipal();
		List<SysRole> roles = sysRoleService.getRoleByUser(sysUser.getId());
		if (null != roles && roles.size() > 0) {
			for (SysRole role : roles) {
				authorizationInfo.addRole(role.getRoleName());
			}
		}
		List<SysMenu> sysMenu = sysMenuService.getMenuByUser(sysUser.getId(), 1);
		if (null != sysMenu && sysMenu.size() > 0) {
			for (SysMenu perm : sysMenu) {
				authorizationInfo.addStringPermission(perm.getPerms());
			}
		}*/
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
       /* String username = usernamePasswordToken.getUsername();
        QueryWrapper<SysUser> ew = new QueryWrapper<SysUser>();
        ew.eq("job_no",username);
        SysUser userInfo = sysUserService.getOne(ew);
        
        // 账户不存在
        if (userInfo == null) {
            throw new UnknownAccountException();
        }
        // 账户被冻结
        if (userInfo.getStatus() == 0) {
            throw new LockedAccountException();
        }*/
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
               /* userInfo,
                userInfo.getPassword(),
                *//*ByteSource.Util.bytes(userInfo.getCredentialsSalt()),*//*
                getName()*/);
        return authenticationInfo;
    }

}
