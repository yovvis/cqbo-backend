package com.cqbo.web.application.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqbo.web.application.service.UserAppService;
import com.cqbo.web.domain.user.entity.User;
import com.cqbo.web.domain.user.service.UserDomainService;
import com.cqbo.web.infrastructure.common.DeleteRequest;
import com.cqbo.web.infrastructure.exception.ErrorCode;
import com.cqbo.web.infrastructure.exception.ThrowUtils;
import com.cqbo.web.interfaces.dto.user.UserQueryRequest;
import com.cqbo.web.interfaces.vo.user.LoginUserVO;
import com.cqbo.web.interfaces.vo.user.UserVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务实现
 */
@Service
public class UserAppServiceImpl implements UserAppService {

    @Resource
    private UserDomainService userDomainService;

    @Override
    public LoginUserVO userLogin(String userAccount, String userPassword) {
        User.validUserLogin(userAccount, userPassword);
        return userDomainService.userLogin(userAccount, userPassword);
    }

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        User.validUserRegister(userAccount, userPassword, checkPassword);
        return userDomainService.userRegister(userAccount, userPassword, checkPassword);
    }

    @Override
    public User getLoginUser() {
        return userDomainService.getLoginUser();
    }

    @Override
    public boolean userLogout() {
        return userDomainService.userLogout();
    }

    @Override
    public String getEncryptPassword(String password) {
        return userDomainService.getEncryptPassword(password);
    }

    @Override
    public LoginUserVO getLoginUserVO(User user) {
        return userDomainService.getLoginUserVO(user);
    }

    @Override
    public UserVO getUserVO(User user) {
        return userDomainService.getUserVO(user);
    }

    @Override
    public List<UserVO> getUserVOList(List<User> userList) {
        return userDomainService.getUserVOList(userList);
    }

    @Override
    public QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest) {
        return userDomainService.getQueryWrapper(userQueryRequest);
    }

    @Override
    public User getUserById(long id) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        User user = userDomainService.getById(id);
        ThrowUtils.throwIf(user == null, ErrorCode.NOT_FOUND_ERROR);
        return user;
    }

    @Override
    public UserVO getUserVOById(long id) {
        return userDomainService.getUserVO(getUserById(id));
    }

    @Override
    public boolean deleteUser(DeleteRequest deleteRequest) {
        ThrowUtils.throwIf(deleteRequest == null || deleteRequest.getId() <= 0, ErrorCode.PARAMS_ERROR);
        return userDomainService.removeById(deleteRequest.getId());
    }

    @Override
    public void updateUser(User user) {
        boolean f = userDomainService.updateById(user);
        ThrowUtils.throwIf(!f, ErrorCode.OPERATION_ERROR);
    }

    @Override
    public Page<UserVO> getUserVOListPage(UserQueryRequest userQueryRequest) {
        ThrowUtils.throwIf(userQueryRequest == null, ErrorCode.PARAMS_ERROR);
        long current = userQueryRequest.getCurrent();
        long size = userQueryRequest.getPageSize();
        Page<User> userPage = userDomainService.page(new Page<>(current, size), userDomainService.getQueryWrapper(userQueryRequest));
        Page<UserVO> userVOPage = new Page<>(current, size, userPage.getTotal());
        List<UserVO> userVOList = userDomainService.getUserVOList(userPage.getRecords());
        userVOPage.setRecords(userVOList);
        return userVOPage;
    }

    @Override
    public List<User> listByIds(List<Long> userIdSet) {
        return userDomainService.listByIds(userIdSet);
    }

    @Override
    public long saveUser(User userEntity) {
        // 默认密码
        final String DEFAULT_PASSWORD = "11111111";
        String encryptPassword = userDomainService.getEncryptPassword(DEFAULT_PASSWORD);
        userEntity.setUserPassword(encryptPassword);
        // 插入数据库
        boolean f = userDomainService.saveUser(userEntity);
        ThrowUtils.throwIf(!f, ErrorCode.OPERATION_ERROR);
        return userEntity.getId();
    }
}
