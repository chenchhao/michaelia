package com.michaelia.emma.dao.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.michaelia.emma.entity.sys.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户与角色对应关系 Mapper 接口
 * </p>
 *
 * @author fengyw
 * @since 2018-10-23
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
	/**
	 * 删除用户角色
	 * @param userIds
	 * @return
	 */
	int deleteUserRoleByUserIds(@Param("userIds") List<Long> userIds);

	/**
	 * 删除用户角色
	 * @param roleIds
	 * @return
	 */
	int deleteUserRoleByRoleIds(@Param("roleIds") List<Long> roleIds);
}
