package com.cqbo.web.model.dto.system.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Schema(description = "用户登录请求参数")
public class UserLoginRequest implements Serializable {

    @Schema(description = "账号")
    private String userAccount;

    @Schema(description = "密码")
    private String userPassword;

    @Serial
    private static final long serialVersionUID = 1L;

}
