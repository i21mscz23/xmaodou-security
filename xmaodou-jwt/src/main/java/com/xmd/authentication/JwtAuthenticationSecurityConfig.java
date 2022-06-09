package com.xmd.authentication;

import com.xmd.filter.AuthenticationFilter;
import com.xmd.handler.jwt.JwtHandler;
import com.xmd.properties.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author lixiao
 * @Date 2020/5/16 14:37
 */
@Component
public class JwtAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private AuthenticationSuccessHandler loginSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler loginFailureHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtHandler jwtHandler;

    @Autowired
    private JwtProperties jwtProperties;



    @Override
    public void configure(HttpSecurity builder) throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtProperties.getLoginUrl());
        jwtAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        jwtAuthenticationFilter.setAuthenticationFailureHandler(loginFailureHandler);
        jwtAuthenticationFilter.setAuthenticationSuccessHandler(loginSuccessHandler);

        //登陆处理设置
        JwtAuthenticationProvider jwtAuthenticationProvider = new JwtAuthenticationProvider();
        jwtAuthenticationProvider.setUserDetailsService(userDetailsService);
        jwtAuthenticationProvider.setPasswordEncoder(passwordEncoder);

        //jwt验证过滤器
        String jwtSecret = jwtProperties.getJwtSecret();
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(jwtHandler,jwtSecret);

        builder.authenticationProvider(jwtAuthenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                //添加过滤器
                .addFilterAfter(authenticationFilter,JwtAuthenticationFilter.class);
    }
}
