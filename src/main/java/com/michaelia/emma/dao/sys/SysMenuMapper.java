package com.michaelia.emma.dao.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.michaelia.emma.entity.sys.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单管理 Mapper 接口
 * </p>
 *
 * @author fengyw
 * @since 2018-10-23
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
	/**
	 * 获取授权菜单
	 * @param userId
	 * @return
	 */
	List<SysMenu> getMenuByUser(@Param("userId") Long userId, @Param("status") Integer status);

	/**
	 * 获取所有菜单
	 * @return
	 */
	List<SysMenu> getAllMenu(@Param("status") Integer status);

	/**
	 * 根据角色获取菜单
	 * @param roleId
	 * @return
	 */
	List<SysMenu> getMenuByRole(@Param("roleId") Long roleId, @Param("status") Integer status);
}
