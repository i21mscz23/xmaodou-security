package com.xmd.social.weibo.connect;

import com.xmd.social.qq.api.QQ;
import com.xmd.social.qq.connect.QQOAuth2Template;
import com.xmd.social.weibo.api.Weibo;
import com.xmd.social.weibo.api.WeiboImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;

/**
 * @Description
 * @Author lixiao
 * @Date 2022/1/27 下午3:08
 */
public class WeiboServiceProvider extends AbstractOAuth2ServiceProvider<Weibo> {


    private static final String URL_AUTHORIZE = "https://api.weibo.com/oauth2/authorize";

    private static final String URL_ACCESS_TOKEN = "https://api.weibo.com/oauth2/access_token";

    public WeiboServiceProvider(String appId, String appSecret) {

        super(new WeiboOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
    }

    @Override
    public Weibo getApi(String accessToken) {
        return new WeiboImpl(accessToken);
    }



}
