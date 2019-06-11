package com.michaelia.emma.service.sys.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.michaelia.emma.dao.sys.SysRoleMapper;
import com.michaelia.emma.entity.sys.SysRole;
import com.michaelia.emma.service.sys.BaseSysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 方法名:          BaseSysRoleServiceImpl
 * 方法功能描述:
 *
 * @param:
 * @return:
 * @Author: 陈超
 * @Create Date:   2019/5/30
 */
@Service
public class BaseSysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements BaseSysRoleService {
    @Override
    public List<SysRole> getRoleByUser(Long userId) {
        return baseMapper.getRoleByUser(userId);
    }
}
