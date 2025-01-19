package com.cqbo.web.application.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqbo.web.domain.role.entity.Role;
import com.cqbo.web.interfaces.dto.role.RoleQueryRequest;
import com.cqbo.web.interfaces.vo.role.RoleVO;

import java.util.List;

/**
 * 角色服务
 *
 * @author yovvis
 * @date 2025/1/5
 */
public interface RoleAppService {
    /**
     * 获取脱敏的资源信息
     *
     * @param menu
     * @return
     */
    RoleVO getRoleVO(Role menu);

    /**
     * 获取脱敏的用户信息
     *
     * @param roleList
     * @return
     */
    List<RoleVO> getRoleVOList(List<Role> roleList);

    /**
     * 获取查询条件
     *
     * @param roleQueryRequest
     * @return
     */
    QueryWrapper<Role> getQueryWrapper(RoleQueryRequest roleQueryRequest);


    // region 增删改查分页

    Page<RoleVO> getRoleVOListPage(RoleQueryRequest roleQueryRequest);

    void removeBatchByIds(List<Long> ids);

    List<Role> list();

    void updateById(Role role);

    boolean removeById(Long id);

    boolean save(Role role);
    // endregion
}
