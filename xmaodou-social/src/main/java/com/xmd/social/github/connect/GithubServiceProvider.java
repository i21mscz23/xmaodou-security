package com.xmd.social.github.connect;

import com.xmd.social.github.api.Github;
import com.xmd.social.github.api.GithubImpl;
import com.xmd.social.qq.api.QQ;
import com.xmd.social.qq.api.QQImpl;
import com.xmd.social.qq.connect.QQOAuth2Template;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/12/13 上午10:00
 */
public class GithubServiceProvider extends AbstractOAuth2ServiceProvider<Github> {


    private static final String URL_AUTHORIZE = "http://github.com/login/oauth/authorize";

    private static final String URL_ACCESS_TOKEN = "https://github.com/login/oauth/access_token";

    public GithubServiceProvider(String appId, String appSecret) {
        super(new GithubOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
    }

    @Override
    public Github getApi(String accessToken) {
        return new GithubImpl(accessToken);
    }
}
