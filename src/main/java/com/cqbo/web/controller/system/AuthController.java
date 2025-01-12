package com.cqbo.web.controller.system;

import cn.hutool.core.util.StrUtil;
import com.cqbo.web.common.BaseResponse;
import com.cqbo.web.common.ResultUtils;
import com.cqbo.web.common.exception.BusinessException;
import com.cqbo.web.common.exception.ErrorCode;
import com.cqbo.web.common.exception.ThrowUtils;
import com.cqbo.web.model.dto.system.user.UserLoginRequest;
import com.cqbo.web.model.dto.system.user.UserRegisterRequest;
import com.cqbo.web.model.entity.system.User;
import com.cqbo.web.model.vo.system.user.LoginUserVO;
import com.cqbo.web.service.system.AuthService;
import com.cqbo.web.service.system.UserService;
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
    private AuthService authService;
    @Resource
    private UserService userService;

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
        LoginUserVO loginUserVO = authService.login(userAccount, userPassword);
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
        long result = authService.register(userAccount, userPassword, checkPassword);
        return ResultUtils.success(result);
    }

    @Operation(summary = "用户注销")
    @PostMapping("/logout")
    public BaseResponse<Boolean> logout() {
        boolean res = authService.logout();
        ThrowUtils.throwIf(!res, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    @Operation(summary = "获取当前登录用户")
    @GetMapping("/getLoginUser")
    public BaseResponse<LoginUserVO> getLoginUser() {
        User loginUser = authService.getLoginUser();
        return ResultUtils.success(userService.getLoginUserVO(loginUser));
    }

}
