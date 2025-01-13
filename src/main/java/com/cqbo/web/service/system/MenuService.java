package com.cqbo.web.service.system;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cqbo.web.model.dto.system.menu.MenuQueryRequest;
import com.cqbo.web.model.entity.system.Menu;
import com.cqbo.web.model.vo.system.menu.MenuTreeVO;
import com.cqbo.web.model.vo.system.menu.MenuVO;

import java.util.List;

/**
 * 资源服务
 *
 * @author yovvis
 * @date 2025/1/5
 */
public interface MenuService extends IService<Menu> {

    /**
     * 获取查询条件
     *
     * @param menuQueryRequestReq
     * @return
     */
    QueryWrapper<Menu> getQueryWrapper(MenuQueryRequest menuQueryRequestReq);


    /**
     * 获取脱敏的资源信息
     *
     * @param menu
     * @return
     */
    MenuVO getMenuVO(Menu menu);

    /**
     * 获取脱敏的用户信息
     *
     * @param menuList
     * @return
     */
    List<MenuVO> getMenuVOList(List<Menu> menuList);

    /**
     * 获取跟菜单
     *
     * @return
     */
    List<MenuTreeVO> getParent();

    /**
     * 根据用户Id查询菜单
     *
     * @param userId
     * @return
     */
    List<Menu> getMenuByUserId(Long userId);

    /**
     * 根据角色Id查询菜单
     *
     * @param roleId
     * @return
     */
    List<Menu> getMenuByRoleId(Long roleId);


}
