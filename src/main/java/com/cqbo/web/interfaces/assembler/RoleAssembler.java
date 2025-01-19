package com.cqbo.web.interfaces.assembler;

import com.cqbo.web.domain.role.entity.Role;
import com.cqbo.web.interfaces.dto.role.RoleAddRequest;
import com.cqbo.web.interfaces.dto.role.RoleUpdateRequest;
import org.springframework.beans.BeanUtils;

/**
 * 角色对象转换
 */
public class RoleAssembler {

    public static Role toRoleEntity(RoleAddRequest request) {
        Role role = new Role();
        BeanUtils.copyProperties(request, role);
        return role;
    }

    public static Role toRoleEntity(RoleUpdateRequest request) {
        Role role = new Role();
        BeanUtils.copyProperties(request, role);
        return role;
    }
}