package com.xmd.social.weibo.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmd.social.qq.api.QQUserInfo;
import com.xmd.social.wechat.api.Weixin;
import com.xmd.social.wechat.api.WeixinUserInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author lixiao
 * @Date 2022/1/26 下午4:37
 */
public class WeiboImpl extends AbstractOAuth2ApiBinding implements Weibo {

    private final Logger logger = LoggerFactory.getLogger(getClass());



    private String openId;


    /**
     * 获取uid（openId）
     */
    private static final String URL_GET_OPENID = "https://api.weibo.com/oauth2/get_token_info";

    /**
     * 获取用户信息的url
     */
    private static final String URL_GET_USER_INFO = "https://api.weibo.com/2/users/show.json";

    /**
     * @param accessToken
     */
    public WeiboImpl(String accessToken) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_GET_OPENID);
        Map<String,String> uriVariables = new HashMap<>();
        uriVariables.put("access_token",accessToken);
        uriVariables.entrySet().stream().forEach(o -> builder.queryParam(o.getKey(),o.getValue()));
        String url = builder.build().encode().toString();

        String tokenInfo = getRestTemplate().getForObject(url, String.class);

        this.openId = StringUtils.substringBetween(tokenInfo, "\"uid\":", ",");


    }

    @Override
    public WeiboUserInfo getUserInfo() {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_GET_USER_INFO);
        Map<String,String> uriVariables = new HashMap<>();
        uriVariables.put("uid",this.openId);

        uriVariables.entrySet().stream().forEach(o -> builder.queryParam(o.getKey(),o.getValue()));
        String url = builder.build().encode().toString();

        String result = getRestTemplate().getForObject(url, String.class);

        logger.info("微博用户信息:{}",result);
        WeiboUserInfo weiboUserInfo = new WeiboUserInfo();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            weiboUserInfo = objectMapper.readValue(result, WeiboUserInfo.class);
            return weiboUserInfo;
        } catch (Exception e) {
            throw new RuntimeException("获取用户信息失败", e);
        }


    }
}
