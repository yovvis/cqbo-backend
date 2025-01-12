package com.cqbo.web.mapper.system;

import com.cqbo.web.model.entity.system.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色资源操作
 *
 * @author yovvis
 * @date 2025/1/5
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    /**
     * 保存角色菜单
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    boolean saveRoleMenu(@Param("roleId") Long roleId, @Param("menuIds") List<Long> menuIds);

}
