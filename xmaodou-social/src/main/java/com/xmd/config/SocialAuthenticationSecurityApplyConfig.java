package com.xmd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/12/13 下午2:48
 */
@Component
public class SocialAuthenticationSecurityApplyConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired(required = false)
    private SpringSocialConfigurer socialSecurityConfig;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        if(socialSecurityConfig != null){
            builder.apply(socialSecurityConfig);
        }
    }
}
