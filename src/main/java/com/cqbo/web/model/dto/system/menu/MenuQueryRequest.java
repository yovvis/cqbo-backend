package com.cqbo.web.model.dto.system.menu;

import com.cqbo.web.common.PageRequest;
import com.cqbo.web.model.entity.system.Menu;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "资源列表查询参数")
public class MenuQueryRequest extends PageRequest implements Serializable {

    @Schema(description = "菜单名称")
    private String title;

    @Schema(description = "权限字段")
    private String code;

    @Schema(description = "路由名称")
    private String name;

    @Schema(description = "路由path")
    private String path;

    @Schema(description = "组件路径")
    private String url;

    @Schema(description = "重定向路径")
    private String redirect;

    @Schema(description = "资源类型：0：目录 1：菜单 2：按钮")
    private Integer type;

    @Schema(description = "是否套用iframe")
    private Integer isIframe;

    @Schema(description = "菜单可见：0-显示;1-隐藏")
    private Integer hideInMenu;

    @Schema(description = "图标")
    private String icon;

    @Schema(description = "国际化")
    private String locale;

    @Schema(description = "父菜单名称")
    private String parentName;


    @Serial
    private static final long serialVersionUID = 1L;

}