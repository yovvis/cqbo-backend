package com.cqbo.web.domain.role.service;

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
public interface RoleDomainService {
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

    // region
    Page<Role> page(Page<Role> rolePage, QueryWrapper<Role> queryWrapper);

    boolean removeBatchByIds(List<Long> ids);

    List<Role> list();

    boolean updateById(Role role);

    boolean removeById(Long id);

    boolean save(Role role);

    // endregion
}
