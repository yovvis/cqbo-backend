package com.cqbo.web.model.vo.system.menu;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Schema(description = "菜单树")
public class MenuTreeVO implements Serializable {

    @Schema(description = "资源视图（脱敏）")
    private Long id;

    @Schema(description = "父Id")
    private Long parentId;

    @Schema(description = "子菜单")
    private List<MenuTreeVO> children;

    @Schema(description = "菜单名称")
    private String title;

    @Schema(description = "角色树文本")
    private String label;

    @Schema(description = "角色树值")
    private Long value;

    @Schema(description = "排序")
    private Integer orderNum;

    @Serial
    private static final long serialVersionUID = 1L;
}