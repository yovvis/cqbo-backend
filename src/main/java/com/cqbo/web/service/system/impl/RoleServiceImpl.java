package com.cqbo.web.service.system.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.cqbo.web.common.exception.BusinessException;
import com.cqbo.web.common.exception.ErrorCode;
import com.cqbo.web.mapper.system.RoleMapper;
import com.cqbo.web.model.dto.system.role.RoleQueryRequest;
import com.cqbo.web.model.entity.system.Role;
import com.cqbo.web.model.vo.system.role.RoleVO;
import com.cqbo.web.service.system.RoleService;
import com.cqbo.web.common.utils.SqlUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色服务实现
 *
 * @author yovvis
 * @date 2025/1/5
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Override
    public QueryWrapper<Role> getQueryWrapper(RoleQueryRequest roleQueryRequest) {
        if (roleQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }

        Long id = roleQueryRequest.getId();
        String roleName = roleQueryRequest.getRoleName();
        String remark = roleQueryRequest.getRemark();
        String sortField = roleQueryRequest.getSortField();
        String sortOrder = roleQueryRequest.getSortOrder();
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ObjectUtil.isNotEmpty(id), "id", id);
        queryWrapper.like(StrUtil.isNotEmpty(roleName), "roleName", roleName);
        queryWrapper.like(StrUtil.isNotBlank(remark), "remark", remark);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals("ascend"), sortField);
        return queryWrapper;
    }

    @Override
    public RoleVO getRoleVO(Role menu) {
        if (menu == null) {
            return null;
        }
        RoleVO menuVO = new RoleVO();
        BeanUtils.copyProperties(menu, menuVO);
        return menuVO;
    }

    @Override
    public List<RoleVO> getRoleVOList(List<Role> menuList) {
        if (CollectionUtils.isEmpty(menuList)) {
            return new ArrayList<>();
        }
        return menuList.stream().map(this::getRoleVO).collect(Collectors.toList());
    }
}
