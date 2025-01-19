package com.cqbo.web.interfaces.dto.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Schema(description = "更新资源请求参数")
public class MenuUpdateRequest implements Serializable {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "菜单名称")
    private String title;

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "地址")
    private String path;

    @Schema(description = "组件")
    private String component;

    @Schema(description = "重定向路径")
    private String redirect;

    @Schema(description = "是否固定页签")
    private Integer affix;

    @Schema(description = "路由名称")
    private String name;

    @Schema(description = "跳转路径")
    private String url;

    @Schema(description = "权限字段")
    private String code;

    @Schema(description = "资源类型：0：目录 1：菜单 2：按钮")
    private Integer type;

    @Serial
    private static final long serialVersionUID = 1L;
}