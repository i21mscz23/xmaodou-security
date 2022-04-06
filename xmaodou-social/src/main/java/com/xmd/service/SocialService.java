package com.xmd.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.SocialUserDetails;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/12/31 下午3:16
 */
public interface SocialService {

    /**
     * 设置UsersConnectionRepository库信息
     * @param connectionFactoryLocator
     * @return
     */
    UsersConnectionRepository setUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator);

    /**
     * 通过providerUserId 获取对应的用户信息
     * @param providerUserId
     * @return
     */
    SocialUserDetails check(String providerUserId);

    /**
     * 社交授权登陆之后返回信息
     * @param authentication
     * @return
     */
    Object onAuthenticationSuccess(Authentication authentication);

    /**
     * 社交授权登陆失败返回的信息
     * @param exception
     * @return
     */
    Object onAuthenticationFailure(AuthenticationException exception);
}
