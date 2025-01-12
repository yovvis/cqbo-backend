package com.cqbo.web.model.entity.system;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 */
@TableName(value = "sys_user")
@Data
public class User implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(description = "账号")
    private String userAccount;

    @Schema(description = "密码")
    private String userPassword;

    @Schema(description = "微信开放平台id")
    private String unionId;

    @Schema(description = "公众号openId")
    private String mpOpenId;

    @Schema(description = "用户昵称")
    private String userName;

    @Schema(description = "用户头像")
    private String userAvatar;

    @Schema(description = "用户简介")
    private String userProfile;

    @Schema(description = "用户角色：user/admin/ban")
    private String userRole;

    @Schema(description = "会员编号")
    private Long vipNumber;

    @Schema(description = "会员兑换码")
    private String vipCode;

    @Schema(description = "会员过期时间")
    private Date vipExpireTime;

    @Schema(description = "分享码")
    private String shareCode;

    @Schema(description = "邀请用户id")
    private Long inviteUser;

    @Schema(description = "编辑时间")
    private Date editTime;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "更新时间")
    private Date updateTime;

    @TableLogic
    private Integer isDelete;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}