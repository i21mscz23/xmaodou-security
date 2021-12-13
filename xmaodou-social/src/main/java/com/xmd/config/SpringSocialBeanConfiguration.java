package com.xmd.config;

import com.xmd.processor.SocialAuthenticationFilterPostProcessor;
import com.xmd.properties.SecuritySocialProperties;
import com.xmd.social.config.CustomSocialConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @Description 设计登陆统一配置
 * @Author lixiao
 * @Date 2021/11/17 下午1:59
 */
@Configuration
public class SpringSocialBeanConfiguration {

    @Autowired
    private SecuritySocialProperties  securitySocialProperties;

    @Autowired
    private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;

    /**
     * SpringSocialConfigurer
     * @return
     */
    @Bean
    public SpringSocialConfigurer socialSecurityConfig() {
        /**
         * 修改社交登陆过滤地址（前缀，默认为 /auth）
         */
        String filterProcessesUrl = securitySocialProperties.getFilterProcessesUrl();
        CustomSocialConfigurer configurer = new CustomSocialConfigurer(filterProcessesUrl);
        configurer.setSocialAuthenticationFilterPostProcessor(socialAuthenticationFilterPostProcessor);
//        configurer.signupUrl(filterProcessesUrl);
        return configurer;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
