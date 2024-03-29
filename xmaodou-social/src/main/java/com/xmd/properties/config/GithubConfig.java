package com.xmd.properties.config;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/12/13 下午1:43
 */
public class GithubConfig {

    private String appId;

    private String appSecret;

    private String providerId = "github";

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
