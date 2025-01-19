package com.cqbo.web.application.service;

import com.cqbo.web.interfaces.dto.rolemenu.RoleMenuAddRequest;

/**
 * 角色资源服务
 *
 * @author yovvis
 * @date 2025/1/5
 */
public interface RoleMenuAppService {

    /**
     * 保存角色菜单
     *
     * @param roleMenuAddRequest
     */
    void saveRoleMenu(RoleMenuAddRequest roleMenuAddRequest);

    // region 增删改查分页

    // endregion
}
