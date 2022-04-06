package com.xmd.config;

import com.xmd.service.SocialService;
import com.xmd.service.impl.DefaultSocialService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author lixiao
 * @Date 2022/1/13 上午11:07
 */
@Configuration
public class SecurityServerBeanConfig {

    @Bean
    public SocialService defaultSocialService(){
        return new DefaultSocialService();
    }
}
