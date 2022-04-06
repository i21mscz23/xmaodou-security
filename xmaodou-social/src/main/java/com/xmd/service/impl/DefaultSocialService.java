package com.xmd.service.impl;


import com.xmd.response.ServerResponse;
import com.xmd.service.SocialService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;



/**
 * @Description
 * @Author lixiao
 * @Date 2021/12/31 下午3:21
 */
public class DefaultSocialService implements SocialService {

    @Override
    public UsersConnectionRepository setUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        InMemoryUsersConnectionRepository repository = new InMemoryUsersConnectionRepository(connectionFactoryLocator);
        return repository;
    }

    @Override
    public SocialUserDetails check(String providerUserId) {
        SocialUser socialUser = new SocialUser(providerUserId, "",
                AuthorityUtils.commaSeparatedStringToAuthorityList(""));
        return socialUser;
    }

    @Override
    public Object onAuthenticationSuccess(Authentication authentication) {

        return ServerResponse.createBySuccessData("登陆成功，未构建token");
    }

    @Override
    public Object onAuthenticationFailure(AuthenticationException exception) {
        String message = exception.getMessage();
        return ServerResponse.createByErrorCodeMessage(message,40001);
    }
}
