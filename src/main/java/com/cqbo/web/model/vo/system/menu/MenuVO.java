package com.cqbo.web.model.vo.system.menu;

import com.cqbo.web.model.entity.system.Menu;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Schema(description = "资源视图（脱敏）")
public class MenuVO implements Serializable {

    @Schema(description = "资源视图（脱敏）")
    private Long id;

    @Schema(description = "父ID")
    private Long parentId;

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

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

    // 不一定要
    @Schema(description = "子菜单")
    private List<MenuVO> children;

    @Schema(description = "值")
    private Long value;

    @Schema(description = "文本")
    private String label;

    @Serial
    private static final long serialVersionUID = 1L;
}