package com.cqbo.web.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqbo.web.domain.role.entity.Role;
import com.cqbo.web.domain.role.repository.RoleRepository;
import com.cqbo.web.infrastructure.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
 * 角色仓储实现
 *
 * @author yovvis
 * @date 2025/1/19
 */
@Service
public class RoleRepositoryImpl extends ServiceImpl<RoleMapper, Role> implements RoleRepository {
}
