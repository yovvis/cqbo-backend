package com.cqbo.web.interfaces.vo.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Schema(description = "资源视图（脱敏）")
public class MenuVO implements Serializable {

    @Schema(description = "资源视图（脱敏）")
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
    private Boolean affix;

    @Schema(description = "父ID")
    private Long parentId;

    @Schema(description = "路由名称")
    private String name;

    @Schema(description = "菜单可见：0-显示;1-隐藏")
    private Boolean hideInMenu;

    @Schema(description = "跳转路径")
    private String url;

    @Schema(description = "面包屑：0-显示;1-隐藏")
    private Boolean hideInBreadcrumb;

    @Schema(description = "子菜单：0-显示;1-隐藏")
    private Boolean hideChildrenInMenu;

    @Schema(description = "保活：0：非 1：是")
    private Boolean keepAlive;

    @Schema(description = "全连接跳转模式")
    private String target;

    @Schema(description = "子菜单")
    private List<MenuVO> children;

    @Schema(description = "父菜单")
    private List<MenuVO> matched;

    @Schema(description = "父级的key")
    private List<String> parentKeys;

    @Schema(description = "权限字段")
    private String code;

    @Schema(description = "资源类型：0：目录 1：菜单 2：按钮")
    private Integer type;

    @Schema(description = "排序")
    private Integer orderNum;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

    @Serial
    private static final long serialVersionUID = 1L;
}