package com.xmd.social.github.connect;

import com.xmd.social.github.api.Github;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/12/13 上午10:06
 */
public class GithubConnectionFactory extends OAuth2ConnectionFactory<Github> {

    public GithubConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new GithubServiceProvider(appId, appSecret), new GithubAdapter());
    }
}
