package com.xmd.properties.config;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/12/6 上午11:13
 */
public class DingTalkConfig {

    private String appId;

    private String appSecret;

    private String providerId;

    private String agentId;

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

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }
}
