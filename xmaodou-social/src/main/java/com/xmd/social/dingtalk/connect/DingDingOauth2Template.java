package com.xmd.social.dingtalk.connect;

import org.springframework.social.oauth2.OAuth2Template;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/12/6 下午2:37
 */
public class DingDingOauth2Template extends OAuth2Template {


    public DingDingOauth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
    }

    public DingDingOauth2Template(String clientId, String clientSecret, String authorizeUrl, String authenticateUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, authenticateUrl, accessTokenUrl);
    }
}
