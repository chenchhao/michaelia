package com.michaelia.emma.service.sys.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.michaelia.emma.dao.sys.SysMenuMapper;
import com.michaelia.emma.entity.sys.SysMenu;
import com.michaelia.emma.service.sys.BaseSysMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 方法名:          BaseSysMenuServiceImpl
 * 方法功能描述:
 *
 * @param:
 * @return:
 * @Author: 陈超
 * @Create Date:   2019/5/30
 */
@Service
public class BaseSysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements BaseSysMenuService {
    @Override
    public List<SysMenu> getMenuByUser(Long userId, Integer status) {
        return baseMapper.getMenuByUser(userId,status);
    }
}
