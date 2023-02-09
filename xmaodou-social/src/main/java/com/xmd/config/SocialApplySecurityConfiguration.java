package com.xmd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @Description 安全配置入口
 * @Author lixiao
 * @Date 2021/11/17 下午5:02
 */
//@Configuration
public class SocialApplySecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private SpringSocialConfigurer socialSecurityConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.apply(socialSecurityConfig).and().authorizeRequests().antMatchers(
                "/social/**","/qqLogin/callback.do","/login.html","binding.html"
        )
                .permitAll().anyRequest().authenticated()
                .and()
                .csrf().disable();
    }

}
