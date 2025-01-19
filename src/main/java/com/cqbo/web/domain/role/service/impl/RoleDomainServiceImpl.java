package com.cqbo.web.domain.role.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqbo.web.domain.role.entity.Role;
import com.cqbo.web.domain.role.repository.RoleRepository;
import com.cqbo.web.domain.role.service.RoleDomainService;
import com.cqbo.web.infrastructure.exception.BusinessException;
import com.cqbo.web.infrastructure.exception.ErrorCode;
import com.cqbo.web.infrastructure.utils.SqlUtils;
import com.cqbo.web.interfaces.dto.role.RoleQueryRequest;
import com.cqbo.web.interfaces.vo.role.RoleVO;
import jakarta.annotation.Resource;
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
public class RoleDomainServiceImpl implements RoleDomainService {
    @Resource
    private RoleRepository roleRepository;

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
    public Page<Role> page(Page<Role> rolePage, QueryWrapper<Role> queryWrapper) {
        return roleRepository.page(rolePage, queryWrapper);
    }

    @Override
    public boolean removeBatchByIds(List<Long> ids) {
        return roleRepository.removeBatchByIds(ids);
    }

    @Override
    public List<Role> list() {
        return roleRepository.list();
    }

    @Override
    public boolean updateById(Role role) {
        return roleRepository.updateById(role);
    }

    @Override
    public boolean removeById(Long id) {
        return roleRepository.removeById(id);
    }

    @Override
    public boolean save(Role role) {
        return roleRepository.save(role);
    }
}
