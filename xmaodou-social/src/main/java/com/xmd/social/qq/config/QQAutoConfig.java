package com.xmd.social.qq.config;

import com.xmd.properties.QQConfig;
import com.xmd.properties.SecuritySocialProperties;
import com.xmd.social.config.CurrentUserHolder;
import com.xmd.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/11/5 上午9:56
 */
@Configuration
public class QQAutoConfig extends SocialConfigurerAdapter {

    @Autowired
    private SecuritySocialProperties securitySocialProperties;

    @Override
    public UserIdSource getUserIdSource() {
        return new CurrentUserHolder();
    }

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer configurer,
                                       Environment environment) {
        configurer.addConnectionFactory(createConnectionFactory());
    }

    protected ConnectionFactory<?> createConnectionFactory() {
        /**
         * providerId 为过滤地址的后面部分（/qqLogin/callback.do）
         */
        QQConfig qq = securitySocialProperties.getQq();
        return new QQConnectionFactory(qq.getProviderId(), qq.getAppId(), qq.getAppSecret(),securitySocialProperties.getRedirectUri());
    }
}
