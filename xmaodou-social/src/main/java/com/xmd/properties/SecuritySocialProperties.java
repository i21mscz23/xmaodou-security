package com.xmd.properties;

import com.xmd.properties.config.GithubConfig;
import com.xmd.properties.config.QQConfig;
import com.xmd.properties.config.WeixinConfig;
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
     * fu
     */
    private String redirectUri;

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

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
}
