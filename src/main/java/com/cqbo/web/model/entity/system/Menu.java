package com.cqbo.web.model.entity.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 资源实体
 *
 * @author yovvis
 * @date 2025/1/5
 */
@TableName("sys_menu")
@Data
public class Menu implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
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

    @Schema(description = "菜单图标")
    private String icon;

    @Schema(description = "国际化")
    private String locale;

    @Schema(description = "上级菜单名称")
    private String parentName;

    @Schema(description = "排序")
    private Integer orderNum;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

    @TableField(exist = false)
    private List<Menu> children;

    @TableField(exist = false)
    private Long value;

    @TableField(exist = false)
    private String label;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}

