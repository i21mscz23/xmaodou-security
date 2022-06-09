package com.xmd.properties;

import com.xmd.user.SecurityConst;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;

import java.util.List;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/5/31 下午5:18
 */
@ConfigurationProperties(prefix = "com.xmd.jwt")
@Primary
public class JwtProperties {



    /**
     * 登陆地址
     */
    private String loginUrl = SecurityConst.LOGIN_URL;

    /**
     * jwt秘钥
     */
    private String jwtSecret = SecurityConst.JWT_SECRET;

    /**
     * jwt过期时间（单位秒）
     */
    private Long jwtExpiration = SecurityConst.JWT_EXPIRATION_TIME;

    /**
     * 请求地址白名单
     */
    private List<String> whiteList;

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getJwtSecret() {
        return jwtSecret;
    }

    public void setJwtSecret(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public Long getJwtExpiration() {
        return jwtExpiration;
    }

    public void setJwtExpiration(Long jwtExpiration) {
        this.jwtExpiration = jwtExpiration;
    }

    public List<String> getWhiteList() {
        return whiteList;
    }

    public void setWhiteList(List<String> whiteList) {
        this.whiteList = whiteList;
    }
}
