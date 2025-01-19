package com.cqbo.web.interfaces.vo.menu;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RouterVo implements Serializable {

    @Schema(description = "路由路径")
    private String path;

    @Schema(description = "组件")
    private String component;

    @Schema(description = "路由名称")
    private String name;

    @Schema(description = "重定向")
    private String redirect;

    @Schema(description = "元数据")
    private Meta meta;

    @Data
    @AllArgsConstructor
    public class Meta {

        @Schema(description = "标题")
        private String title;
        @Schema(description = "图标")
        private String icon;
        @Schema(description = "角色")
        private Object[] roles;
    }

    @Schema(description = "子路由")
    private List<RouterVo> children = new ArrayList<>();

    @Serial
    private static final long serialVersionUID = 1L;

}
