package com.cqbo.web.model.dto.system.role;

import com.cqbo.web.common.PageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "角色查询请求参数")
public class RoleQueryRequest extends PageRequest implements Serializable {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "角色状态 0：正常 1：禁用")
    private Integer status;

    @Schema(description = "备注")
    private String remark;

    @Serial
    private static final long serialVersionUID = 1L;

}