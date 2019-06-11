package com.michaelia.emma.service.sys;

import com.baomidou.mybatisplus.extension.service.IService;
import com.michaelia.emma.entity.sys.SysRole;

import java.util.List;

/**
 * 方法名:          BaseSysRoleService
 * 方法功能描述:
 *
 * @param:
 * @return:
 * @Author: 陈超
 * @Create Date:   2019/5/30
 */
public interface BaseSysRoleService extends IService<SysRole> {

    List<SysRole> getRoleByUser(Long userId);
}
