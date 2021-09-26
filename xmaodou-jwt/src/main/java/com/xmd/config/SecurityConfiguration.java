package com.xmd.config;

import com.xmd.annotation.EnableSecurityJwt;
import com.xmd.authentication.JwtAuthenticationSecurityConfig;
import com.xmd.user.SecurityConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/9/8 下午3:05
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {



    @Autowired
    private AuthenticationSuccessHandler loginSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler loginFailureHandler;

    @Autowired
    private JwtAuthenticationSecurityConfig jwtAuthenticationSecurityConfig;

    @Autowired
    private AuthenticationEntryPoint userAuthenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler userAccessDeniedHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                //登陆地址
//                .loginPage(SecurityConst.LOGIN_PAGE)
                //结果处理器
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
            .and()
                //权限处理器
                .exceptionHandling().authenticationEntryPoint(userAuthenticationEntryPoint)
                .accessDeniedHandler(userAccessDeniedHandler)
            .and()
                .authorizeRequests()
                .antMatchers(
//                        SecurityConst.LOGIN_PAGE,
                        SecurityConst.LOGIN_URL
                )
                .permitAll()
                //权限表达式
                .anyRequest().access("@permissionService.hasPermission(request, authentication)")
            .and()
                //添加自定义认证流程
                .apply(jwtAuthenticationSecurityConfig)
            .and()
                //禁用session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .csrf().disable();


    }
}
