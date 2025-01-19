package com.cqbo.web.interfaces.controller.system;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqbo.web.application.service.MenuAppService;
import com.cqbo.web.domain.menu.entity.Menu;
import com.cqbo.web.infrastructure.common.BaseResponse;
import com.cqbo.web.infrastructure.common.DeleteRequest;
import com.cqbo.web.infrastructure.common.ResultUtils;
import com.cqbo.web.infrastructure.exception.ErrorCode;
import com.cqbo.web.infrastructure.exception.ThrowUtils;
import com.cqbo.web.interfaces.assembler.MenuAssembler;
import com.cqbo.web.interfaces.dto.menu.MenuAddRequest;
import com.cqbo.web.interfaces.dto.menu.MenuQueryRequest;
import com.cqbo.web.interfaces.dto.menu.MenuUpdateRequest;
import com.cqbo.web.interfaces.vo.menu.MenuVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
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
    private MenuAppService menuAppService;

    // region 增删改查

    @Operation(summary = "添加资源")
    @PostMapping(value = "/add")
    public BaseResponse<Long> addMenu(@RequestBody MenuAddRequest menuAddRequest) {
        ThrowUtils.throwIf(menuAddRequest == null, ErrorCode.PARAMS_ERROR);
        Menu menuEntity = MenuAssembler.toMenuEntity(menuAddRequest);
        return ResultUtils.success(menuAppService.save(menuEntity));
    }

    @Operation(summary = "删除资源")
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteMenu(@RequestBody DeleteRequest deleteRequest) {
        ThrowUtils.throwIf(deleteRequest == null || deleteRequest.getId() < 0, ErrorCode.PARAMS_ERROR);
        menuAppService.removeById(deleteRequest.getId());
        return ResultUtils.success(true);
    }

    @Operation(summary = "修改资源")
    @PostMapping(value = "/update")
    public BaseResponse<Boolean> updateMenu(@RequestBody MenuUpdateRequest menuUpdateRequest) {
        ThrowUtils.throwIf(menuUpdateRequest == null || menuUpdateRequest.getId() < 0, ErrorCode.PARAMS_ERROR);
        Menu menuEntity = MenuAssembler.toMenuEntity(menuUpdateRequest);
        menuAppService.updateById(menuEntity);
        return ResultUtils.success(true);
    }

    @Operation(summary = "分页获取资源列表")
    @PostMapping("/listPageVO")
    public BaseResponse<Page<MenuVO>> listMenuPage(@RequestBody MenuQueryRequest menuQueryRequestReq) {
        ThrowUtils.throwIf(menuQueryRequestReq == null, ErrorCode.PARAMS_ERROR);
        Page<MenuVO> menuVOPage = menuAppService.getMenuVOListPage(menuQueryRequestReq);
        return ResultUtils.success(menuVOPage);
    }

    @Operation(summary = "获取菜单列表(脱敏)")
    @PostMapping("/getMenuList")
    public BaseResponse<List<MenuVO>> getMenuList() {
        List<MenuVO> menuVOList = menuAppService.listMenu();
        return ResultUtils.success(menuVOList);
    }
    // endregion
}
