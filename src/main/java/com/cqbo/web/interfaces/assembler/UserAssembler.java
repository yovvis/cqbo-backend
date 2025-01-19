package com.cqbo.web.interfaces.assembler;

import com.cqbo.web.domain.user.entity.User;
import com.cqbo.web.interfaces.dto.user.UserAddRequest;
import com.cqbo.web.interfaces.dto.user.UserUpdateMyRequest;
import com.cqbo.web.interfaces.dto.user.UserUpdateRequest;
import org.springframework.beans.BeanUtils;

/**
 * 用户对象转换
 */
public class UserAssembler {

    public static User toUserEntity(UserAddRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        return user;
    }

    public static User toUserEntity(UserUpdateRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        return user;
    }

    public static User toUserEntity(UserUpdateMyRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        return user;
    }
}