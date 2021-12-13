package com.xmd.social.github.config;

import com.xmd.social.config.CurrentUserHolder;
import com.xmd.social.github.connect.GithubConnectionFactory;
import com.xmd.social.qq.connect.QQConnectionFactory;
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
public class GithubAutoConfig extends SocialConfigurerAdapter {

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
        return new GithubConnectionFactory("github", "Iv1.9a1d0cbafea21da5", "ce2ff0d7357e60cb05f4479b2591ba96b36bf038");
    }
}
