package com.xmd.jwt;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/12/24 下午2:40
 */
@Component("jwtTokenEnhancerEnhance")
public class JwtTokenEnhancer implements TokenEnhancer {


    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Map<String, Object> info = new HashMap<>();

        /*String username = ((User) oAuth2Authentication.getUserAuthentication().getPrincipal()).getUsername();
        Integer integer = 1;
        info.put("userID", integer);
        //往jwt中添加信息
        ((DefaultOAuth2AccessToken)oAuth2AccessToken).setAdditionalInformation(info);*/

        return oAuth2AccessToken;
    }
}
