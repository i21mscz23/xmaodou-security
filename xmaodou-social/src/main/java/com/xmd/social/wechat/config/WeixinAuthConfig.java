package com.xmd.social.wechat.config;

import com.xmd.properties.QQConfig;
import com.xmd.properties.SecuritySocialProperties;
import com.xmd.properties.WeixinConfig;
import com.xmd.social.wechat.connect.WeixinConnectionFactory;
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
 * @Date 2021/12/13 下午4:49
 */
@Configuration
@ConditionalOnProperty(prefix = "com.xmd.social.weixin", name = "app-id")
public class WeixinAuthConfig extends SocialConfigurerAdapter {

    @Autowired
    private SecuritySocialProperties securitySocialProperties;


    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer configurer,
                                       Environment environment) {
        configurer.addConnectionFactory(createConnectionFactory());
    }

    protected ConnectionFactory<?> createConnectionFactory() {
        /**
         * providerId 为过滤地址的后面部分（/qqLogin/callback.do）
         */
        WeixinConfig weixin = securitySocialProperties.getWeixin();
        return new WeixinConnectionFactory(weixin.getProviderId(), weixin.getAppId(), weixin.getAppSecret());
    }


}
