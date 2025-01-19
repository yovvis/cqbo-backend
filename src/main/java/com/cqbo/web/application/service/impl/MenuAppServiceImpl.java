package com.cqbo.web.application.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqbo.web.application.service.MenuAppService;
import com.cqbo.web.domain.menu.entity.Menu;
import com.cqbo.web.domain.menu.service.MenuDomainService;
import com.cqbo.web.infrastructure.exception.ErrorCode;
import com.cqbo.web.infrastructure.exception.ThrowUtils;
import com.cqbo.web.interfaces.dto.menu.MenuQueryRequest;
import com.cqbo.web.interfaces.vo.menu.MenuTreeVO;
import com.cqbo.web.interfaces.vo.menu.MenuVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资源服务实现
 *
 * @author yovvis
 * @date 2025/1/5
 */
@Service
public class MenuAppServiceImpl implements MenuAppService {

    @Resource
    private MenuDomainService menuDomainService;

    @Override
    public MenuVO getMenuVO(Menu menu) {
        return menuDomainService.getMenuVO(menu);
    }

    @Override
    public List<MenuVO> getMenuVOList(List<Menu> menuList) {
        return menuDomainService.getMenuVOList(menuList);
    }

    @Override
    public QueryWrapper<Menu> getQueryWrapper(MenuQueryRequest menuQueryRequest) {
        return menuDomainService.getQueryWrapper(menuQueryRequest);
    }

    @Override
    public List<MenuTreeVO> getParent() {
        return menuDomainService.getParent();
    }

    @Override
    public List<Menu> getMenuByUserId(Long userId) {
        return menuDomainService.getMenuByUserId(userId);
    }

    @Override
    public List<Menu> getMenuByRoleId(Long roleId) {
        return menuDomainService.getMenuByRoleId(roleId);
    }

    @Override
    public long save(Menu menu) {
        boolean f = menuDomainService.save(menu);
        ThrowUtils.throwIf(!f, ErrorCode.SYSTEM_ERROR);
        return menu.getId();
    }

    @Override
    public void removeById(Long id) {
        boolean f = menuDomainService.removeById(id);
        ThrowUtils.throwIf(!f, ErrorCode.SYSTEM_ERROR);
    }

    @Override
    public void updateById(Menu menu) {
        boolean f = menuDomainService.updateById(menu);
        ThrowUtils.throwIf(!f, ErrorCode.SYSTEM_ERROR);
    }

    @Override
    public Page<MenuVO> getMenuVOListPage(MenuQueryRequest menuQueryRequest) {
        int current = menuQueryRequest.getCurrent();
        int pageSize = menuQueryRequest.getPageSize();
        Page<Menu> menuPage = menuDomainService.page(new Page<>(current, pageSize), menuDomainService.getQueryWrapper(menuQueryRequest));
        Page<MenuVO> menuVOPage = new Page<>(current, pageSize, menuPage.getTotal());
        menuVOPage.setRecords(getMenuVOList(menuPage.getRecords()));
        return menuVOPage;
    }

    @Override
    public List<MenuVO> listMenu() {
        return menuDomainService.listMenu();
    }

}
