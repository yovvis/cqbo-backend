package com.cqbo.web.service.system.impl;

import com.cqbo.web.mapper.system.UserRoleMapper;
import com.cqbo.web.model.entity.system.UserRole;
import com.cqbo.web.service.system.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 用户角色服务实现
 *
 * @author yovvis
 * @date 2025/1/5
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
