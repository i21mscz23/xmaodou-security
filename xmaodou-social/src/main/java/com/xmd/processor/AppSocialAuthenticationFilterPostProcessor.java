package com.xmd.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author lixiao
 * @Date 2020/5/25 16:39
 */
@Component
public class AppSocialAuthenticationFilterPostProcessor implements SocialAuthenticationFilterPostProcessor {

    @Autowired
    private AuthenticationSuccessHandler socialAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler socialAuthenticationFailureHandler;

    @Override
    public void process(SocialAuthenticationFilter socialAuthenticationFilter) {
        socialAuthenticationFilter.setAuthenticationFailureHandler(socialAuthenticationFailureHandler);
        socialAuthenticationFilter.setAuthenticationSuccessHandler(socialAuthenticationSuccessHandler);
    }
}
