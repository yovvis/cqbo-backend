package com.cqbo.web.application.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqbo.web.domain.user.entity.User;
import com.cqbo.web.infrastructure.common.DeleteRequest;
import com.cqbo.web.interfaces.dto.user.UserQueryRequest;
import com.cqbo.web.interfaces.vo.user.LoginUserVO;
import com.cqbo.web.interfaces.vo.user.UserVO;

import java.util.List;

/**
 * 用户服务
 */
public interface UserAppService {
    /**
     * 用户登录
     *
     * @param userAccount
     * @param userPassword
     * @return
     */
    LoginUserVO userLogin(String userAccount, String userPassword);

    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 获取当前登录用户
     *
     * @return
     */
    User getLoginUser();

    /**
     * 用户注销
     *
     * @return
     */
    boolean userLogout();

    /**
     * 获取加密密码
     *
     * @param password
     * @return
     */
    String getEncryptPassword(String password);

    /**
     * 获取脱敏的已登录用户信息
     *
     * @return
     */
    LoginUserVO getLoginUserVO(User user);

    /**
     * 获取脱敏的用户信息
     *
     * @param user
     * @return
     */
    UserVO getUserVO(User user);

    /**
     * 获取脱敏的用户信息
     *
     * @param userList
     * @return
     */
    List<UserVO> getUserVOList(List<User> userList);

    /**
     * 获取查询条件
     *
     * @param userQueryRequest
     * @return
     */
    QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest);

    // region 增删改查分页

    User getUserById(long id);

    UserVO getUserVOById(long id);

    boolean deleteUser(DeleteRequest deleteRequest);

    void updateUser(User user);

    Page<UserVO> getUserVOListPage(UserQueryRequest userQueryRequest);

    List<User> listByIds(List<Long> userIdSet);

    long saveUser(User userEntity);

    // endregion

}
