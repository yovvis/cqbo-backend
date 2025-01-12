package com.cqbo.web.controller.system;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqbo.web.common.BaseResponse;
import com.cqbo.web.common.DeleteRequest;
import com.cqbo.web.common.ResultUtils;
import com.cqbo.web.common.exception.BusinessException;
import com.cqbo.web.common.exception.ErrorCode;
import com.cqbo.web.common.exception.ThrowUtils;
import com.cqbo.web.model.dto.system.role.RoleAddRequest;
import com.cqbo.web.model.dto.system.role.RoleQueryRequest;
import com.cqbo.web.model.dto.system.role.RoleUpdateRequest;
import com.cqbo.web.model.dto.system.rolemenu.RoleMenuAddRequest;
import com.cqbo.web.model.entity.system.Role;
import com.cqbo.web.model.vo.system.role.RoleVO;
import com.cqbo.web.model.vo.system.role.SelectItem;
import com.cqbo.web.service.system.RoleMenuService;
import com.cqbo.web.service.system.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 角色接口
 *
 * @author yovvis
 * @date 2025/1/5
 */
@Tag(name = "RoleController", description = "角色接口")
@RestController
@RequestMapping("/rest/sys/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @Resource
    private RoleMenuService roleMenuService;

    // region 增删改查

    /**
     * 新增角色
     *
     * @param roleAddRequest
     * @return
     */
    @Operation(summary = "新增角色")
    @PostMapping("/add")
    public BaseResponse<Boolean> add(@RequestBody RoleAddRequest roleAddRequest) {
        if (roleAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Role role = new Role();
        BeanUtils.copyProperties(roleAddRequest, role);
        boolean res = roleService.save(role);
        ThrowUtils.throwIf(!res, new BusinessException(ErrorCode.OPERATION_ERROR));
        return ResultUtils.success(true);
    }

    /**
     * 删除角色数据
     *
     * @return
     */
    @PostMapping("/delete")
    @Operation(summary = "删除角色数据")
    public BaseResponse<Boolean> delete(@RequestBody DeleteRequest deleteRequest) {
        if (deleteRequest.getId() == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean res = roleService.removeById(deleteRequest.getId());
        ThrowUtils.throwIf(!res, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    /**
     * 更新角色
     *
     * @param roleUpdateRequest
     * @return
     */
    @Operation(summary = "更新角色")
    @PostMapping("/update")
    public BaseResponse<Boolean> update(@RequestBody RoleUpdateRequest roleUpdateRequest) {
        if (roleUpdateRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Role role = new Role();
        BeanUtils.copyProperties(roleUpdateRequest, role);
        boolean res = roleService.updateById(role);
        ThrowUtils.throwIf(!res, new BusinessException(ErrorCode.OPERATION_ERROR));
        return ResultUtils.success(true);
    }

    /**
     * 分页获取角色列表
     *
     * @param roleQueryRequest
     * @return
     */
    @Operation(summary = "分页获取角色列表")
    @PostMapping("/listPage")
    public BaseResponse<Page<RoleVO>> listRolePage(@RequestBody RoleQueryRequest roleQueryRequest) {
        long current = roleQueryRequest.getCurrent();
        long size = roleQueryRequest.getPageSize();
        Page<Role> rolePage = roleService.page(new Page<>(current, size), roleService.getQueryWrapper(roleQueryRequest));
        Page<RoleVO> roleVOPage = new Page<>(current, size, rolePage.getTotal());
        roleVOPage.setRecords(roleService.getRoleVOList(rolePage.getRecords()));
        return ResultUtils.success(roleVOPage);
    }

    /**
     * 批量删除角色
     *
     * @param ids
     * @return
     */
    @PostMapping("/deleteByIds")
    @Operation(summary = "批量删除角色")
    public BaseResponse<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean res = roleService.removeBatchByIds(ids);
        ThrowUtils.throwIf(!res, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }
    // endregion

    /**
     * 角色下拉列表
     *
     * @return
     */
    @GetMapping("/selectRoleList")
    @Operation(summary = "角色下拉列表")
    public BaseResponse<List<SelectItem>> selectRoleList() {
        List<Role> list = roleService.list();
        // 返回的值
        List<SelectItem> selectItems = new ArrayList<>();
        Optional.ofNullable(list).orElse(new ArrayList<>())
                .forEach(item -> {
                    SelectItem vo = new SelectItem();
                    vo.setCheck(false);
                    vo.setLabel(item.getRoleName());
                    vo.setValue(item.getId());
                    selectItems.add(vo);
                });
        return ResultUtils.success(selectItems);
    }

    /**
     * 保存角色菜单
     *
     * @param roleMenuAddRequest
     * @return
     */
    @PostMapping("/saveRoleMenu")
    @Operation(summary = "保存角色菜单")
    public BaseResponse<Boolean> saveRoleMenu(@RequestBody RoleMenuAddRequest roleMenuAddRequest) {
        roleMenuService.saveRoleMenu(roleMenuAddRequest);
        return ResultUtils.success(true);
    }
}
