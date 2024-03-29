package com.xmd.social.qq.connect;

import com.xmd.social.qq.api.QQ;
import com.xmd.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/11/15 下午9:39
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {


    private String appId;

    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId, String appSecret,String redirectUri) {

        super(new QQOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN,redirectUri));
        this.appId = appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken, appId);
    }
}
