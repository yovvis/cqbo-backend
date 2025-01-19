package com.cqbo.web.domain.user.entity;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.*;
import com.cqbo.web.infrastructure.exception.BusinessException;
import com.cqbo.web.infrastructure.exception.ErrorCode;
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

    /**
     * 用户注册校验
     *
     * @param userAccount
     * @param userPassword
     * @param checkPassword
     */
    public static void validUserRegister(String userAccount, String userPassword, String checkPassword) {
        if (StrUtil.hasBlank(userAccount, userPassword, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (userAccount.length() < 5) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户账号过短");
        }
        if (userPassword.length() < 8 || checkPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户密码过短");
        }
    }

    /**
     * 用户登录校验
     *
     * @param userAccount
     * @param userPassword
     */
    public static void validUserLogin(String userAccount, String userPassword) {
        // 1. 校验
        if (StrUtil.hasBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (userAccount.length() < 5) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号错误");
        }
        if (userPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码错误");
        }
    }
}