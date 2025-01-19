package com.cqbo.web.domain.menu.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqbo.web.domain.menu.entity.Menu;
import com.cqbo.web.domain.menu.repository.MenuRepository;
import com.cqbo.web.domain.menu.service.MenuDomainService;
import com.cqbo.web.infrastructure.exception.BusinessException;
import com.cqbo.web.infrastructure.exception.ErrorCode;
import com.cqbo.web.infrastructure.utils.SqlUtils;
import com.cqbo.web.interfaces.dto.menu.MenuQueryRequest;
import com.cqbo.web.interfaces.vo.menu.MakeMenuTree;
import com.cqbo.web.interfaces.vo.menu.MenuTreeVO;
import com.cqbo.web.interfaces.vo.menu.MenuVO;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 资源服务实现
 *
 * @author yovvis
 * @date 2025/1/5
 */
@Service
public class MenuDomainServiceImpl implements MenuDomainService {

    @Resource
    private MenuRepository menuRepository;

    @Override
    public MenuVO getMenuVO(Menu menu) {
        if (menu == null) {
            return null;
        }
        MenuVO menuVO = new MenuVO();
        BeanUtils.copyProperties(menu, menuVO);
        return menuVO;
    }

    @Override
    public List<MenuVO> getMenuVOList(List<Menu> menuList) {
        if (CollectionUtils.isEmpty(menuList)) {
            return new ArrayList<>();
        }
        return menuList.stream().map(this::getMenuVO).collect(Collectors.toList());
    }

    @Override
    public QueryWrapper<Menu> getQueryWrapper(MenuQueryRequest menuQueryRequest) {
        if (menuQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }

        String path = menuQueryRequest.getPath();
        String name = menuQueryRequest.getName();
        String sortField = menuQueryRequest.getSortField();
        String sortOrder = menuQueryRequest.getSortOrder();
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StrUtil.isNotBlank(path), "path", path);
        queryWrapper.like(StrUtil.isNotBlank(name), "name", name);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals("ascend"), sortField);
        return queryWrapper;
    }

    @Override
    public List<MenuTreeVO> getParent() {
        String[] type = {"0", "1"};
        List<String> strings = Arrays.asList(type);
        QueryWrapper<Menu> query = new QueryWrapper<>();
        query.lambda().in(Menu::getType, strings).orderByAsc(Menu::getOrderNum);
        List<Menu> menuList = menuRepository.list(query);
        // 组装顶级树
        MenuTreeVO menuTreeVO = new MenuTreeVO();
        menuTreeVO.setTitle("根菜单");
        menuTreeVO.setLabel("根菜单");
        menuTreeVO.setParentId(-1L);
        menuTreeVO.setId(0L);
        menuTreeVO.setValue(0L);
        List<MenuVO> menuVOList = this.getMenuVOList(menuList);
        // 组装菜单树
        return MakeMenuTree.makeTree(menuVOList, -1L);
    }

    @Override
    public List<Menu> getMenuByUserId(Long userId) {
        return menuRepository.getMenuByUserId(userId);
    }

    @Override
    public List<Menu> getMenuByRoleId(Long roleId) {
        return menuRepository.getMenuByRoleId(roleId);
    }

    @Override
    public boolean save(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public boolean removeById(Long id) {
        return menuRepository.removeById(id);
    }

    @Override
    public boolean updateById(Menu menu) {
        return menuRepository.updateById(menu);
    }

    @Override
    public Page<Menu> page(Page<Menu> menuPage, QueryWrapper<Menu> queryWrapper) {
        return menuRepository.page(menuPage, queryWrapper);
    }

    @Override
    public List<MenuVO> listMenu() {
        List<Menu> menuList = menuRepository.lambdaQuery().eq(Menu::getType, "1").list();
        return this.getMenuVOList(menuList);
    }

}
