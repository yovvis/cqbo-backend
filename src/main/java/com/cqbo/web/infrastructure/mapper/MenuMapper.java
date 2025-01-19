package com.cqbo.web.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cqbo.web.domain.menu.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 资源操作
 *
 * @author yovvis
 * @date 2025/1/5
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据用户Id查询菜单
     *
     * @param userId
     * @return
     */
    List<Menu> getMenuByUserId(@Param("userId") Long userId);

    /**
     * 根据角色Id查询菜单
     *
     * @param roleId
     * @return
     */
    List<Menu> getMenuByRoleId(@Param("roleId") Long roleId);
}