package com.xmd.config;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import com.xmd.authentication.JwtAuthenticationSecurityConfig;
import com.xmd.properties.JwtProperties;
import com.xmd.user.SecurityConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

import java.util.List;

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

    @Autowired(required = false)
    private SpringSocialConfigurer socialSecurityConfig;

    @Autowired
    private AuthenticationEntryPoint userAuthenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler userAccessDeniedHandler;

    @Autowired
    private JwtProperties jwtProperties;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //白名单
        List<String> whiteList = jwtProperties.getWhiteList();
        if(CollectionUtil.isEmpty(whiteList)){
            whiteList = Lists.newArrayList();
        }
        whiteList.add(SecurityConst.LOGIN_URL);
        String[] whiteArray = whiteList.toArray(new String[]{});

        //配置社交登陆
        if(socialSecurityConfig != null){
            http.apply(socialSecurityConfig);
        }

        http
            .formLogin()
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
                        whiteArray
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
                .cors()
            .and()
                .csrf().disable()
               ;


    }
}
