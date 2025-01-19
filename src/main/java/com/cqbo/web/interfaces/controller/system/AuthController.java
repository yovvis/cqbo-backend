package com.cqbo.web.interfaces.controller.system;

import cn.hutool.core.util.StrUtil;
import com.cqbo.web.application.service.UserAppService;
import com.cqbo.web.domain.user.entity.User;
import com.cqbo.web.infrastructure.common.BaseResponse;
import com.cqbo.web.infrastructure.common.ResultUtils;
import com.cqbo.web.infrastructure.exception.BusinessException;
import com.cqbo.web.infrastructure.exception.ErrorCode;
import com.cqbo.web.infrastructure.exception.ThrowUtils;
import com.cqbo.web.interfaces.dto.user.UserLoginRequest;
import com.cqbo.web.interfaces.dto.user.UserRegisterRequest;
import com.cqbo.web.interfaces.vo.user.LoginUserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * 授权管理
 *
 * @author tenyon
 * @date 2025/1/6
 */
@Tag(name = "AuthController", description = "授权管理接口")
@RestController
@RequestMapping("/rest/sys/auth")
public class AuthController {
    @Resource
    private UserAppService userAppService;

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public BaseResponse<LoginUserVO> login(@RequestBody UserLoginRequest userLoginRequest) {
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StrUtil.hasBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        LoginUserVO loginUserVO = userAppService.userLogin(userAccount, userPassword);
        return ResultUtils.success(loginUserVO);
    }

    @Operation(summary = "用户注册")
    @PostMapping("/register")
    public BaseResponse<Long> register(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StrUtil.hasBlank(userAccount, userPassword, checkPassword)) {
            return null;
        }
        long result = userAppService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtils.success(result);
    }

    @Operation(summary = "用户注销")
    @PostMapping("/logout")
    public BaseResponse<Boolean> logout() {
        boolean f = userAppService.userLogout();
        ThrowUtils.throwIf(!f, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    @Operation(summary = "获取当前登录用户")
    @GetMapping("/getLoginUser")
    public BaseResponse<LoginUserVO> getLoginUser() {
        User loginUser = userAppService.getLoginUser();
        return ResultUtils.success(userAppService.getLoginUserVO(loginUser));
    }

}
