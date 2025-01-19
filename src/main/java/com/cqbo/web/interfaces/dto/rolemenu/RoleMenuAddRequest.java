package com.cqbo.web.interfaces.dto.rolemenu;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@Schema(description = "新增角色菜单请求参数")
public class RoleMenuAddRequest implements Serializable {

    @Schema(description = "角色id")
    private Long roleId;

    @Schema(description = "菜单ids")
    private List<Long> list;
    @Serial
    private static final long serialVersionUID = 1L;
}
