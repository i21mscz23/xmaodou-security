package com.xmd.social.weibo.connect;


import com.xmd.social.weibo.api.Weibo;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @Description
 * @Author lixiao
 * @Date 2022/1/27 下午3:22
 */
public class WeiboConnectionFactory extends OAuth2ConnectionFactory<Weibo> {

    public WeiboConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new WeiboServiceProvider(appId, appSecret), new WeiboAdapter());
    }
}
