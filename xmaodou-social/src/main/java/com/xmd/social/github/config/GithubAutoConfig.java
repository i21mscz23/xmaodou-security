package com.xmd.social.github.config;

import com.xmd.properties.GithubConfig;
import com.xmd.properties.SecuritySocialProperties;
import com.xmd.properties.SocialProperties;
import com.xmd.social.config.CurrentUserHolder;
import com.xmd.social.github.connect.GithubConnectionFactory;
import com.xmd.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/12/13 上午10:08
 */
@Configuration
@ConditionalOnProperty(prefix = "com.xmd.social.github", name = "app-id")
public class GithubAutoConfig extends SocialConfigurerAdapter {

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
        GithubConfig github = securitySocialProperties.getGithub();
        return new GithubConnectionFactory(github.getProviderId(), github.getAppId(), github.getAppSecret());
    }
}
