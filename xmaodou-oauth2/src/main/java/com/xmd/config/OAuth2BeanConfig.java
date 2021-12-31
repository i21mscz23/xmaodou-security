package com.xmd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

/**
 * @Description spring bean配置
 * @Author lixiao
 * @Date 2021/12/24 下午1:30
 */
@Configuration
public class OAuth2BeanConfig {

    private static final String KEY_FILE_NAME = "i21mscz23.key";

    private static final String KEY_PASSWORD = "i21mscz23";

    private static final String KEY_ALIAS = "i21mscz23";

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtTokenEnhancer());
    }

    @Bean
    public JwtAccessTokenConverter jwtTokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        //字符串方式加密
        converter.setSigningKey("123456");

        //秘钥方式加密
//        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(KEY_FILE_NAME), KEY_PASSWORD.toCharArray());
//        converter.setKeyPair(keyStoreKeyFactory.getKeyPair(KEY_ALIAS));
        return converter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
