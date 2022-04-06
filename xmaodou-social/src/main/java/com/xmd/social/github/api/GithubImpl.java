package com.xmd.social.github.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmd.social.qq.api.QQUserInfo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.web.client.RestTemplate;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/12/13 上午9:49
 */
public class GithubImpl extends AbstractOAuth2ApiBinding implements Github{

    /**
     * 获取用户信息地址
     */
    private static final String URL_GET_USERINFO = "https://api.github.com/user";

    private String accessToken;


    public GithubImpl(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public GithubInfo getUserInfo() {
        RestTemplate restTemplate = getRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "bearer " + this.accessToken);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> exchange = restTemplate.exchange(URL_GET_USERINFO, HttpMethod.GET, entity, String.class);
        String body = exchange.getBody();

        GithubInfo userInfo = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            userInfo = objectMapper.readValue(body, GithubInfo.class);
            return userInfo;
        } catch (Exception e) {
            throw new RuntimeException("获取用户信息失败", e);
        }
    }
}
