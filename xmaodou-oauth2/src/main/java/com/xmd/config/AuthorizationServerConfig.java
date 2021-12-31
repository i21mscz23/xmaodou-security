package com.xmd.config;


import com.xmd.granter.ClientCredentialsGranter;
import com.xmd.service.CacheClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


/**
 * @Description 认证服务器配置
 * @Author lixiao
 * @Date 2021/12/15 下午4:30
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService jdbcUserDetailsService;

    @Autowired
    private TokenEnhancer jwtTokenEnhancerEnhance;

    @Autowired
    private JwtAccessTokenConverter jwtTokenEnhancer;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private AuthenticationManager authenticationManagerBean;

    private AuthorizationCodeServices authorizationCodeServices;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        //通过链式加强器增加jwt功能
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancers = new ArrayList<>();
        if(jwtTokenEnhancerEnhance != null ){
            enhancers.add(jwtTokenEnhancerEnhance);
        }
//        List<TokenGranter> defaultTokenGranters = endpoints.getDefaultTokenGranters();


        enhancers.add(jwtTokenEnhancer);
        enhancerChain.setTokenEnhancers(enhancers);


        endpoints
                .userDetailsService(jdbcUserDetailsService)
                .tokenStore(tokenStore)
                .tokenGranter(new CompositeTokenGranter(getTokenGranters(authenticationManagerBean,endpoints.getTokenServices(), new CacheClientDetailsService(dataSource), endpoints.getOAuth2RequestFactory())))
                .tokenEnhancer(enhancerChain)
                .accessTokenConverter(jwtTokenEnhancer)
                .authenticationManager(authenticationManager);
    }



    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.withClientDetails(new CacheClientDetailsService(dataSource));
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                //获取秘钥需要客户端认证
                .tokenKeyAccess("isAuthenticated()")
                .checkTokenAccess("isAuthenticated()");
    }


    private List<TokenGranter> getTokenGranters(AuthenticationManager authenticationManager, AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        List<TokenGranter> tokenGranters = new ArrayList<>();
        // 添加授权码模式
        tokenGranters.add(new AuthorizationCodeTokenGranter(tokenServices, authorizationCodeServices, clientDetailsService, requestFactory));
        // 添加刷新令牌的模式
        tokenGranters.add(new RefreshTokenGranter(tokenServices, clientDetailsService, requestFactory));
        // 添加隐式授权模式
        tokenGranters.add(new ImplicitTokenGranter(tokenServices, clientDetailsService, requestFactory));
        // 添加客户端模式
        tokenGranters.add(new ClientCredentialsTokenGranter(tokenServices, clientDetailsService, requestFactory));
//        // 添加自定义授权模式（实际是密码模式的复制）
//        tokenGranters.add(new ClientCredentialsGranter(authenticationManagerBean,tokenServices, clientDetailsService, requestFactory));
//        tokenGranters.add(new WeChatAbstractTokenGranter(tokenServices, clientDetailsService, requestFactory));
        if (authenticationManager != null) {
            // 添加密码模式
            tokenGranters.add(new ResourceOwnerPasswordTokenGranter(authenticationManager, tokenServices, clientDetailsService, requestFactory));
        }
        return tokenGranters;
    }

    private AuthorizationCodeServices authorizationCodeServices() {
        if (authorizationCodeServices == null) {
            authorizationCodeServices = new InMemoryAuthorizationCodeServices();
        }
        return authorizationCodeServices;
    }
}
