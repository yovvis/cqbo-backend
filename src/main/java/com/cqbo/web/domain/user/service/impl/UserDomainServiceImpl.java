package com.cqbo.web.domain.user.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cqbo.web.domain.user.constant.BmsConstant;
import com.cqbo.web.domain.user.constant.UserConstant;
import com.cqbo.web.domain.user.entity.User;
import com.cqbo.web.domain.user.repository.UserRepository;
import com.cqbo.web.domain.user.service.UserDomainService;
import com.cqbo.web.domain.user.valueobject.UserRoleEnum;
import com.cqbo.web.infrastructure.exception.BusinessException;
import com.cqbo.web.infrastructure.exception.ErrorCode;
import com.cqbo.web.infrastructure.exception.ThrowUtils;
import com.cqbo.web.infrastructure.utils.SqlUtils;
import com.cqbo.web.interfaces.dto.user.UserQueryRequest;
import com.cqbo.web.interfaces.vo.user.LoginUserVO;
import com.cqbo.web.interfaces.vo.user.UserVO;
import com.cqbo.web.shared.auth.StpKit;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户服务实现
 */
@Service
public class UserDomainServiceImpl implements UserDomainService {
    @Resource
    private UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(UserDomainServiceImpl.class);

    @Override
    public LoginUserVO userLogin(String userAccount, String userPassword) {
        // 加密
        String encryptPassword = this.getEncryptPassword(userPassword);
        // 查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword", encryptPassword);
        User user = userRepository.getOne(queryWrapper);
        // 用户不存在
        if (user == null) {
            logger.info("user login failed, userAccount cannot match userPassword");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不存在或密码错误");
        }
        // 3. 记录用户的登录态
        StpKit.SYSTEM.login(user.getId());
        StpKit.SYSTEM.getSession().set(UserConstant.USER_LOGIN_STATE, user);
        return this.getLoginUserVO(user);
    }

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        // 密码和校验密码相同
        if (!userPassword.equals(checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次输入的密码不一致");
        }
        synchronized (userAccount.intern()) {
            // 1.账户不能重复
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("userAccount", userAccount);
            long count = userRepository.count(queryWrapper);
            if (count > 0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "账号重复");
            }
            // 2.加密
            String encryptPassword = getEncryptPassword(userPassword);
            // 3.插入数据
            User user = new User();
            user.setUserAccount(userAccount);
            user.setUserPassword(encryptPassword);
            user.setUserName("游客123");
            user.setUserRole(UserRoleEnum.USER.getValue());
            boolean saveResult = userRepository.save(user);
            if (!saveResult) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "注册失败，数据库错误");
            }
            return user.getId();
        }
    }

    /**
     * 获取当前登录用户
     *
     * @return
     */
    @Override
    public User getLoginUser() {
        ThrowUtils.throwIf(!StpKit.SYSTEM.isLogin(), ErrorCode.NOT_LOGIN_ERROR);
        // 先判断是否已登录
        Object userObj = StpKit.SYSTEM.getSession().get(UserConstant.USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null || currentUser.getId() == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return currentUser;
    }

    @Override
    public boolean userLogout() {
        ThrowUtils.throwIf(!StpKit.SYSTEM.isLogin(), ErrorCode.NOT_LOGIN_ERROR);
        StpKit.SYSTEM.getSession().removeTokenSign(UserConstant.USER_LOGIN_STATE);
        StpKit.SYSTEM.logout();
        return true;
    }

    @Override
    public String getEncryptPassword(String password) {
        return DigestUtils.md5DigestAsHex((BmsConstant.ENCRYPT_SALT + password).getBytes());
    }

    @Override
    public LoginUserVO getLoginUserVO(User user) {
        if (user == null) {
            return null;
        }
        LoginUserVO loginUserVO = new LoginUserVO();
        BeanUtils.copyProperties(user, loginUserVO);
        return loginUserVO;
    }

    @Override
    public UserVO getUserVO(User user) {
        if (user == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    public List<UserVO> getUserVOList(List<User> userList) {
        if (CollectionUtils.isEmpty(userList)) {
            return new ArrayList<>();
        }
        return userList.stream().map(this::getUserVO).collect(Collectors.toList());
    }

    @Override
    public QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest) {
        if (userQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        Long id = userQueryRequest.getId();
        String userName = userQueryRequest.getUserName();
        String userAccount = userQueryRequest.getUserAccount();
        String userProfile = userQueryRequest.getUserProfile();
        String userRole = userQueryRequest.getUserRole();
        String sortField = userQueryRequest.getSortField();
        String sortOrder = userQueryRequest.getSortOrder();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ObjectUtil.isNotEmpty(id), "id", id);
        queryWrapper.eq(StrUtil.isNotBlank(userAccount), "userAccount", userAccount);
        queryWrapper.eq(StrUtil.isNotBlank(userRole), "userRole", userRole);
        queryWrapper.like(StrUtil.isNotBlank(userProfile), "userProfile", userProfile);
        queryWrapper.like(StrUtil.isNotBlank(userName), "userName", userName);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals("ascend"), sortField);
        return queryWrapper;
    }

    @Override
    public Boolean removeById(Long id) {
        return userRepository.removeById(id);
    }

    @Override
    public boolean updateById(User user) {
        return userRepository.updateById(user);
    }

    @Override
    public User getById(long id) {
        return userRepository.getById(id);
    }

    @Override
    public Page<User> page(Page<User> userPage, QueryWrapper<User> queryWrapper) {
        return userRepository.page(userPage, queryWrapper);
    }

    @Override
    public List<User> listByIds(List<Long> userIdSet) {
        return userRepository.listByIds(userIdSet);
    }

    @Override
    public boolean saveUser(User userEntity) {
        return userRepository.save(userEntity);
    }
}
