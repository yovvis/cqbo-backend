package com.cqbo.web.manager.auth;

import cn.dev33.satoken.stp.StpInterface;
import cn.hutool.core.util.StrUtil;
import com.cqbo.web.common.exception.BusinessException;
import com.cqbo.web.common.exception.ErrorCode;
import com.cqbo.web.model.entity.system.Menu;
import com.cqbo.web.model.entity.system.User;
import com.cqbo.web.service.system.MenuService;
import com.cqbo.web.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 描述
 *
 * @author yovvis
 * @date 2025/1/4
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        System.out.println("查询用户权限了");
        // 根据id查询用户用户信息
        User user = userService.getById((Serializable) loginId);
        List<Menu> menuList = null;
        // 判断是否是超级管理员
        if(StrUtil.isNotEmpty(user.getUserRole()) && "1".equals(user.getUserRole())) {
            menuList = menuService.list();
        } else {
//            menuList = menuService.getMenuByUserId(user.getId());
        }
        if(menuList.stream().filter(Objects::nonNull).collect(Collectors.toList()).isEmpty()) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR,"该用户对应的角色未分配菜单权限，请用管理员账号登录分配菜单");
        }
        // 获取菜单表的code字段
//        List<String> permissionList = Optional.ofNullable(menuList).orElse(new ArrayList<>())
//                .stream()
//                .filter(item -> item != null && StrUtil.isNotEmpty(item.getCode()))
//                .map(item -> item.getCode())
//                .collect(Collectors.toList());
//        // 设置返回值
//        return permissionList;
        return null;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 本 list 仅做模拟，实际项目中要根据具体业务逻辑来查询角色
        List<String> list = new ArrayList<String>();
        list.add("admin");
        list.add("super-admin");
        return list;
    }
}
