package com.xmd.social.weibo.config;

import com.xmd.properties.SecuritySocialProperties;
import com.xmd.properties.WeiboConfig;
import com.xmd.properties.WeixinConfig;
import com.xmd.social.wechat.connect.WeixinConnectionFactory;
import com.xmd.social.weibo.connect.WeiboConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @Description
 * @Author lixiao
 * @Date 2022/1/26 下午4:41
 */
@Configuration
@ConditionalOnProperty(prefix = "com.xmd.social.weibo", name = "app-id")
public class WeiboAuthConfig extends SocialConfigurerAdapter {


    @Autowired
    private SecuritySocialProperties securitySocialProperties;


    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer configurer,
                                       Environment environment) {
        configurer.addConnectionFactory(createConnectionFactory());
    }

    protected ConnectionFactory<?> createConnectionFactory() {
        /**
         * providerId 为过滤地址的后面部分（/qqLogin/weibo）
         */
        WeiboConfig weixin = securitySocialProperties.getWeibo();
        return new WeiboConnectionFactory(weixin.getProviderId(), weixin.getAppId(), weixin.getAppSecret());
    }
}
