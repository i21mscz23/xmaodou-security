package com.xmd.config;

import com.xmd.service.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;


import javax.sql.DataSource;


/**
 * @Description
 * @Author lixiao
 * @Date 2021/11/15 下午9:44
 */
@ConditionalOnProperty(prefix = "com.xmd.social", name = "enable",havingValue = "true")
@Configuration
@EnableSocial
public class SocialConfiguration extends SocialConfigurerAdapter {

    /**
     * 社交登陆connect 表前缀
     */
    public static final String SOCIAL_TABLE_PREFIX = "social_";

    @Autowired
    private DataSource dataSource;

    @Autowired(required = false)
    private ConnectionSignUp connectionSignUp;

    @Autowired
    private SocialService socialService;



    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {

        UsersConnectionRepository usersConnectionRepository = socialService.setUsersConnectionRepository(connectionFactoryLocator);


        //Encryptors.noOpText()数据加解密,noOpText明文显示
//        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
//                connectionFactoryLocator, Encryptors.noOpText());
//        repository.setTablePrefix(SOCIAL_TABLE_PREFIX);
//
//        if(connectionSignUp != null) {
//            repository.setConnectionSignUp(connectionSignUp);
//        }
        return usersConnectionRepository;
    }


//    @Bean
//    public UsersConnectionRepository usersConnectionRepository (ConnectionFactoryLocator connectionFactoryLocator) {
//        InMemoryUsersConnectionRepository repository = new InMemoryUsersConnectionRepository (connectionFactoryLocator);
//        repository.setConnectionSignUp (connectionSignUp);
//        return repository;
//    }

//    @Bean
//    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
//        return new ProviderSignInUtils(connectionFactoryLocator,
//                getUsersConnectionRepository(connectionFactoryLocator)) {
//        };
//    }
}
