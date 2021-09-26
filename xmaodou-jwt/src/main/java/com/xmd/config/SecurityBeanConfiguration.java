package com.xmd.config;

import com.xmd.annotation.EnableSecurityJwt;
import com.xmd.authority.AccessDecisionService;
import com.xmd.authority.PermissionService;
import com.xmd.handler.intercept.UserAccessDeniedHandler;
import com.xmd.handler.intercept.UserAuthenticationEntryPoint;
import com.xmd.handler.jwt.JwtHandler;
import com.xmd.handler.login.LoginFailureHandler;
import com.xmd.handler.login.LoginSuccessHandler;
import com.xmd.service.SecurityService;
import com.xmd.service.impl.DefaultSecurityService;
import com.xmd.user.JdbcDetailsService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

/**
 * @Description bean 配置类
 * @Author lixiao
 * @Date 2021/9/8 下午3:24
 */
@Configuration
public class SecurityBeanConfiguration {

    /**
     * 密码加密工具
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * jwt 处理器
     * @return
     */
    @Bean
    public JwtHandler  jwtHandler(){

        return new JwtHandler();
    }

    /**
     * 当token有效时，权限拒绝是回调
     * @return
     */
    @Bean
    public AccessDeniedHandler userAccessDeniedHandler(){
        return new UserAccessDeniedHandler();
    }

    /**
     * 当匿名登陆情况下，权限驳回（token无效、无token）
     * @return
     */
    @Bean
    public AuthenticationEntryPoint userAuthenticationEntryPoint(){
        return new UserAuthenticationEntryPoint();
    }

    @Bean
    public SimpleUrlAuthenticationFailureHandler loginFailureHandler(){
        return new LoginFailureHandler();
    }

    @Bean
    public SimpleUrlAuthenticationSuccessHandler loginSuccessHandler(){
        return new LoginSuccessHandler();
    }

    @Bean
    public UserDetailsService jdbcDetailsService(){

        return new JdbcDetailsService();
    }

    @Bean
    public PermissionService permissionService(){
        return new AccessDecisionService();
    }

    /**
     * 默认接口实现形式
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(SecurityService.class)
    public SecurityService defaultSecurityService(){

        return new DefaultSecurityService();
    }
}
