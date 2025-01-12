package com.cqbo.web.service.system.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqbo.web.common.exception.BusinessException;
import com.cqbo.web.common.exception.ErrorCode;
import com.cqbo.web.mapper.system.MenuMapper;
import com.cqbo.web.model.dto.system.menu.MenuQueryRequest;
import com.cqbo.web.model.entity.system.Menu;
import com.cqbo.web.model.vo.system.menu.MakeMenuTree;
import com.cqbo.web.model.vo.system.menu.MenuVO;
import com.cqbo.web.service.system.MenuService;
import com.cqbo.web.service.system.UserService;
import com.cqbo.web.common.utils.SqlUtils;
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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Resource
    private UserService userService;

    @Override
    public QueryWrapper<Menu> getQueryWrapper(MenuQueryRequest menuQueryRequest) {
        if (menuQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }

        String path = menuQueryRequest.getPath();
        String name = menuQueryRequest.getName();
        String parentName = menuQueryRequest.getParentName();
        String sortField = menuQueryRequest.getSortField();
        String sortOrder = menuQueryRequest.getSortOrder();
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StrUtil.isNotBlank(path), "path", path);
        queryWrapper.like(StrUtil.isNotBlank(name), "name", name);
        queryWrapper.like(StrUtil.isNotBlank(parentName), "parentName", parentName);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals("ascend"), sortField);
        return queryWrapper;
    }

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
    public List<Menu> getParent() {
        String[] type = {"0", "1"};
        List<String> strings = Arrays.asList(type);
        QueryWrapper<Menu> query = new QueryWrapper<>();
        query.lambda().in(Menu::getType, strings).orderByAsc(Menu::getOrderNum);
        List<Menu> menuList = this.baseMapper.selectList(query);
        // 组装顶级树
        Menu menu = new Menu();
        menu.setTitle("根菜单");
        menu.setLabel("根菜单");
        menu.setParentId(-1L);
        menu.setId(0L);
        menu.setValue(0L);
        menuList.add(menu);
        // 组装菜单树
        return MakeMenuTree.makeTree(menuList, -1L);
    }

    @Override
    public List<Menu> getMenuByUserId(Long userId) {
        return this.baseMapper.getMenuByUserId(userId);
    }

    @Override
    public List<Menu> getMenuByRoleId(Long roleId) {
        return this.baseMapper.getMenuByRoleId(roleId);
    }

}
