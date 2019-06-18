package com.michaelia.emma.dao.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.michaelia.emma.entity.sys.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author fengyw
 * @since 2018-10-23
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
	/**
     * 根据用户id获取角色
     * @param userId
     * @return
     */
    List<SysRole> getRoleByUser(@Param("userId") Long userId);
    
    List<SysRole> getUserRoleNoLogin(String jobNo);
}
