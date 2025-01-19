package com.cqbo.web.infrastructure.repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqbo.web.domain.menu.entity.Menu;
import com.cqbo.web.domain.menu.repository.MenuRepository;
import com.cqbo.web.infrastructure.mapper.MenuMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单仓储实现
 */
@Service
public class MenuRepositoryImpl extends ServiceImpl<MenuMapper, Menu> implements MenuRepository {
    @Override
    public List<Menu> getMenuByUserId(Long userId) {
        return baseMapper.getMenuByUserId(userId);
    }

    @Override
    public List<Menu> getMenuByRoleId(Long roleId) {
        return baseMapper.getMenuByRoleId(roleId);
    }
}
