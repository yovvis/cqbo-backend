package com.cqbo.web.application.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqbo.web.application.service.RoleMenuAppService;
import com.cqbo.web.domain.rolemenu.entity.RoleMenu;
import com.cqbo.web.infrastructure.exception.BusinessException;
import com.cqbo.web.infrastructure.exception.ErrorCode;
import com.cqbo.web.infrastructure.exception.ThrowUtils;
import com.cqbo.web.infrastructure.mapper.RoleMenuMapper;
import com.cqbo.web.interfaces.dto.rolemenu.RoleMenuAddRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 角色资源服务实现
 *
 * @author yovvis
 * @date 2025/1/5
 */
@Service
public class RoleMenuAppServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuAppService {

    @Override
    @Transactional
    public void saveRoleMenu(RoleMenuAddRequest roleMenuAddRequest) {
        // 先删除
        QueryWrapper<RoleMenu> query = new QueryWrapper<>();
        query.lambda().eq(RoleMenu::getRoleId, roleMenuAddRequest.getRoleId());
        this.baseMapper.delete(query);
        // 再保存
        boolean f = this.baseMapper.saveRoleMenu(roleMenuAddRequest.getRoleId(), roleMenuAddRequest.getList());
        ThrowUtils.throwIf(!f, new BusinessException(ErrorCode.OPERATION_ERROR));
    }
}