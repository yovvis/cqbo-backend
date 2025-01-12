package com.cqbo.web.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqbo.web.common.BaseResponse;
import com.cqbo.web.common.DeleteRequest;
import com.cqbo.web.common.ResultUtils;
import com.cqbo.web.common.exception.BusinessException;
import com.cqbo.web.common.exception.ErrorCode;
import com.cqbo.web.common.exception.ThrowUtils;
import com.cqbo.web.manager.auth.StpKit;
import com.cqbo.web.model.dto.system.menu.MenuAddRequest;
import com.cqbo.web.model.dto.system.menu.MenuQueryRequest;
import com.cqbo.web.model.dto.system.menu.MenuUpdateRequest;
import com.cqbo.web.model.entity.system.Menu;
import com.cqbo.web.model.vo.system.menu.MenuVO;
import com.cqbo.web.service.system.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 资源接口
 *
 * @author yovvis
 * @date 2025/1/5
 */
@Tag(name = "MenuController", description = "资源接口")
@RestController
@RequestMapping("/rest/sys/menu")
public class MenuController {
    @Resource
    private MenuService menuService;

    // region 增删改查

    @Operation(summary = "添加资源")
    @PostMapping(value = "/add")
    public BaseResponse<Long> addMenu(@RequestBody MenuAddRequest menuAddRequest) {
        if (menuAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuAddRequest, menu);

        boolean res = menuService.save(menu);
        ThrowUtils.throwIf(!res, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(menu.getId());
    }

    @Operation(summary = "删除资源")
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteMenu(@RequestBody DeleteRequest deleteRequest) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean res = menuService.removeById(deleteRequest.getId());
        return ResultUtils.success(res);
    }

    @Operation(summary = "修改资源")
    @PostMapping(value = "/update")
    public BaseResponse updateMenu(@RequestBody MenuUpdateRequest menuUpdateRequest) {
        if (menuUpdateRequest == null || menuUpdateRequest.getId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请选择资源");
        }
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuUpdateRequest, menu);
        boolean res = menuService.updateById(menu);
        ThrowUtils.throwIf(!res, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    @Operation(summary = "分页获取资源列表")
    @PostMapping("/listPage")
    public BaseResponse<Page<Menu>> listPermissionPage(@RequestBody MenuQueryRequest menuQueryRequestReq) {
        long current = menuQueryRequestReq.getCurrent();
        long size = menuQueryRequestReq.getPageSize();
        Page<Menu> menuPage = menuService.page(new Page<>(current, size),
                menuService.getQueryWrapper(menuQueryRequestReq));
        return ResultUtils.success(menuPage);
    }

    @Operation(summary = "获取菜单列表(脱敏)")
    @PostMapping("/getMenuList")
    public BaseResponse<List<MenuVO>> getMenuList() {
        ThrowUtils.throwIf(!StpKit.SYSTEM.isLogin(), ErrorCode.NOT_LOGIN_ERROR);
        List<Menu> menuList = menuService.lambdaQuery().eq(Menu::getType, "1").list();
        List<MenuVO> menuVOList = menuService.getMenuVOList(menuList);
        return ResultUtils.success(menuVOList);
    }

    // endregion

}
