package com.xmd.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/11/5 上午10:06
 */
@ConfigurationProperties(prefix = "com.xmd.social")
public class SecuritySocialProperties {


    /**
     * SocialAuthenticationFilter 拦截处理的地址
     */
    private String filterProcessesUrl = "/auth";

    /**
     * qq配置
     */
    private QQConfig qq;

    /**
     * github配置
     */
    private GithubConfig github;

    /**
     * 微信配置
     */
    private WeixinConfig weixin;

    /**
     * 微博配置
     */
    private WeiboConfig weibo;



    public String getFilterProcessesUrl() {
        return filterProcessesUrl;
    }

    public void setFilterProcessesUrl(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    public QQConfig getQq() {
        return qq;
    }

    public void setQq(QQConfig qq) {
        this.qq = qq;
    }

    public GithubConfig getGithub() {
        return github;
    }

    public void setGithub(GithubConfig github) {
        this.github = github;
    }

    public WeixinConfig getWeixin() {
        return weixin;
    }

    public void setWeixin(WeixinConfig weixin) {
        this.weixin = weixin;
    }

    public WeiboConfig getWeibo() {
        return weibo;
    }

    public void setWeibo(WeiboConfig weibo) {
        this.weibo = weibo;
    }
}
