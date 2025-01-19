package com.cqbo.web.domain.menu.repository;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cqbo.web.domain.menu.entity.Menu;

import java.util.List;

/**
 * 菜单仓储
 */
public interface MenuRepository extends IService<Menu> {
    List<Menu> getMenuByUserId(Long userId);

    List<Menu> getMenuByRoleId(Long roleId);
}
