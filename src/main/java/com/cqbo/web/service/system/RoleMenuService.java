package com.cqbo.web.service.system;

import com.cqbo.web.model.dto.system.rolemenu.RoleMenuAddRequest;
import com.cqbo.web.model.entity.system.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 角色资源服务
 *
 * @author yovvis
 * @date 2025/1/5
 */
public interface RoleMenuService extends IService<RoleMenu> {

    /**
     * 保存角色菜单
     *
     * @param roleMenuAddRequest
     */
    void saveRoleMenu(RoleMenuAddRequest roleMenuAddRequest);
}
