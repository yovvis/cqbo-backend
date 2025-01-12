package com.cqbo.web.service.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqbo.web.common.exception.BusinessException;
import com.cqbo.web.common.exception.ErrorCode;
import com.cqbo.web.common.exception.ThrowUtils;
import com.cqbo.web.mapper.system.RoleMenuMapper;
import com.cqbo.web.model.dto.system.rolemenu.RoleMenuAddRequest;
import com.cqbo.web.model.entity.system.RoleMenu;
import com.cqbo.web.service.system.RoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色资源服务实现
 *
 * @author yovvis
 * @date 2025/1/5
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Override
    @Transactional
    public void saveRoleMenu(RoleMenuAddRequest roleMenuAddRequest) {
        // 先删除
        QueryWrapper<RoleMenu> query = new QueryWrapper<>();
        query.lambda().eq(RoleMenu::getRoleId, roleMenuAddRequest.getRoleId());
        this.baseMapper.delete(query);
        // 再保存
        boolean res = this.baseMapper.saveRoleMenu(roleMenuAddRequest.getRoleId(), roleMenuAddRequest.getList());
        ThrowUtils.throwIf(!res, new BusinessException(ErrorCode.OPERATION_ERROR));
    }
}