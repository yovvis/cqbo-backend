package com.cqbo.web.interfaces.controller.system;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqbo.web.application.service.RoleAppService;
import com.cqbo.web.application.service.RoleMenuAppService;
import com.cqbo.web.domain.role.entity.Role;
import com.cqbo.web.infrastructure.common.BaseResponse;
import com.cqbo.web.infrastructure.common.DeleteRequest;
import com.cqbo.web.infrastructure.common.ResultUtils;
import com.cqbo.web.infrastructure.exception.ErrorCode;
import com.cqbo.web.infrastructure.exception.ThrowUtils;
import com.cqbo.web.interfaces.assembler.RoleAssembler;
import com.cqbo.web.interfaces.dto.role.RoleAddRequest;
import com.cqbo.web.interfaces.dto.role.RoleQueryRequest;
import com.cqbo.web.interfaces.dto.role.RoleUpdateRequest;
import com.cqbo.web.interfaces.dto.rolemenu.RoleMenuAddRequest;
import com.cqbo.web.interfaces.vo.role.RoleVO;
import com.cqbo.web.interfaces.vo.role.SelectItem;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
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
    private RoleAppService roleAppService;

    @Resource
    private RoleMenuAppService roleMenuAppService;

    // region 增删改查

    @Operation(summary = "新增角色")
    @PostMapping("/add")
    public BaseResponse<Long> addRole(@RequestBody RoleAddRequest roleAddRequest) {
        ThrowUtils.throwIf(roleAddRequest == null, ErrorCode.PARAMS_ERROR);
        Role roleEntity = RoleAssembler.toRoleEntity(roleAddRequest);
        return ResultUtils.success(roleAppService.save(roleEntity));
    }

    @PostMapping("/delete")
    @Operation(summary = "删除角色数据")
    public BaseResponse<Boolean> deleteRole(@RequestBody DeleteRequest deleteRequest) {
        ThrowUtils.throwIf(deleteRequest.getId() == null || deleteRequest.getId() < 0, ErrorCode.PARAMS_ERROR);
        roleAppService.removeById(deleteRequest.getId());
        return ResultUtils.success(true);
    }

    @Operation(summary = "更新角色")
    @PostMapping("/update")
    public BaseResponse<Boolean> updateRole(@RequestBody RoleUpdateRequest roleUpdateRequest) {
        ThrowUtils.throwIf(roleUpdateRequest == null, ErrorCode.PARAMS_ERROR);
        Role roleEntity = RoleAssembler.toRoleEntity(roleUpdateRequest);
        roleAppService.updateById(roleEntity);
        return ResultUtils.success(true);
    }

    @Operation(summary = "分页获取角色列表")
    @PostMapping("/listPageVO")
    public BaseResponse<Page<RoleVO>> listRoleVOPage(@RequestBody RoleQueryRequest roleQueryRequest) {
        ThrowUtils.throwIf(roleQueryRequest == null, ErrorCode.PARAMS_ERROR);
        Page<RoleVO> roleVOPage = roleAppService.getRoleVOListPage(roleQueryRequest);
        return ResultUtils.success(roleVOPage);
    }

    @PostMapping("/deleteByIds")
    @Operation(summary = "批量删除角色")
    public BaseResponse<Boolean> deleteByIds(@RequestBody List<Long> ids) {
        ThrowUtils.throwIf(CollUtil.isEmpty(ids), ErrorCode.PARAMS_ERROR);
        roleAppService.removeBatchByIds(ids);
        return ResultUtils.success(true);
    }
    // endregion

    @GetMapping("/selectRoleList")
    @Operation(summary = "角色下拉列表")
    public BaseResponse<List<SelectItem>> selectRoleList() {
        List<Role> list = roleAppService.list();
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

    @PostMapping("/saveRoleMenu")
    @Operation(summary = "保存角色菜单")
    public BaseResponse<Boolean> saveRoleMenu(@RequestBody RoleMenuAddRequest roleMenuAddRequest) {
        roleMenuAppService.saveRoleMenu(roleMenuAddRequest);
        return ResultUtils.success(true);
    }
}
