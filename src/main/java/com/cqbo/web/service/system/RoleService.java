package com.cqbo.web.service.system;

import com.cqbo.web.model.dto.system.role.RoleQueryRequest;
import com.cqbo.web.model.entity.system.Role;
import com.cqbo.web.model.vo.system.role.RoleVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 角色服务
 *
 * @author yovvis
 * @date 2025/1/5
 */
public interface RoleService extends IService<Role> {

    /**
     * 获取查询条件
     *
     * @param roleQueryRequest
     * @return
     */
    QueryWrapper<Role> getQueryWrapper(RoleQueryRequest roleQueryRequest);


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
}
