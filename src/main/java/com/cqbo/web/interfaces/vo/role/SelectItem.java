package com.cqbo.web.interfaces.vo.role;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Schema(description = "角色选择")
public class SelectItem implements Serializable {

    @Schema(description = "值")
    private Long value;

    @Schema(description = "文本")
    private String label;

    @Schema(description = "是否检查")
    private boolean check;

    @Serial
    private static final long serialVersionUID = 1L;
}
