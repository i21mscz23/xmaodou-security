package com.xmd.config;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.collect.Lists;
import com.xmd.annotation.AnonymousAccess;
import com.xmd.authentication.JwtAuthenticationSecurityConfig;
import com.xmd.properties.JwtProperties;
import com.xmd.properties.WebProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private WebProperties webProperties;


    @Override
    public void configure(WebSecurity web) throws Exception {
        List<String> ignoring = webProperties.getIgnoring();
        if(CollectionUtil.isNotEmpty(ignoring)){
            //所需要用到的静态资源，允许访问(如swagger)
            web.ignoring().antMatchers( ignoring.stream().toArray(String[]::new));
        }

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String[] whiteArray = new String[]{};
        Set<String> whiteSet = new HashSet<>();

        //白名单 配置文件形式
        List<String> whiteList = jwtProperties.getWhiteList();
        if(CollectionUtil.isNotEmpty(whiteList)){
            whiteSet.addAll(whiteList);
        }

        //白名单 注解方式
        Map<RequestMappingInfo, HandlerMethod> handlerMethodMap = applicationContext.getBean(RequestMappingHandlerMapping.class).getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> infoEntry : handlerMethodMap.entrySet()) {
            HandlerMethod handlerMethod = infoEntry.getValue();
            AnonymousAccess anonymousAccess = handlerMethod.getMethodAnnotation(AnonymousAccess.class);
            if (null != anonymousAccess) {
                Set<String> patterns = infoEntry.getKey().getPatternsCondition().getPatterns();
                whiteSet.addAll(patterns);
            }
        }

        if(CollectionUtil.isNotEmpty(whiteSet)){
            whiteArray = whiteSet.toArray(new String[]{});
        }

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
