package com.cqbo.web.model.vo.system.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@Schema(description = "角色视图（脱敏）")
public class RoleVO implements Serializable {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "id")
    private String roleName;

    @Schema(description = "id")
    private String remark;

    @Schema(description = "扩展字段")
    private String type;

    @Schema(description = "id")
    private Date createTime;

    @Schema(description = "id")
    private Date updateTime;

    @Serial
    private static final long serialVersionUID = 1L;
}