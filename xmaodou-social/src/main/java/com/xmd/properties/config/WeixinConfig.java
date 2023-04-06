package com.xmd.properties.config;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/12/13 下午4:52
 */
public class WeixinConfig extends SocialProperties {

    /**
     * 第三方id，用来决定发起第三方登录的url，默认是 weixin。
     */
    private String providerId = "weixin";

    /**
     * @return the providerId
     */
    public String getProviderId() {
        return providerId;
    }

    /**
     * @param providerId the providerId to set
     */
    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
