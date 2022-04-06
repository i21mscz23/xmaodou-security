package com.xmd.config;

import com.xmd.processor.SocialAuthenticationFilterPostProcessor;
import com.xmd.properties.SecuritySocialProperties;
import com.xmd.service.SocialService;
import com.xmd.service.impl.DefaultSocialService;
import com.xmd.social.config.CustomSocialConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
@EnableConfigurationProperties({SecuritySocialProperties.class})
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
    @ConditionalOnProperty(prefix = "com.xmd.social", name = "enable",havingValue = "true")
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
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    @ConditionalOnMissingBean(SocialService.class)
    public SocialService defaultSocialService(){
        return new DefaultSocialService();
    }


}
