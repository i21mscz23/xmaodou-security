package com.xmd.social.weibo.connect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * @Description
 * @Author lixiao
 * @Date 2022/1/27 下午3:12
 */
public class WeiboOAuth2Template extends OAuth2Template {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public WeiboOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);

        setUseParametersForClientAuthentication(true);
    }

    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        String responseStr = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);

        logger.info("微博，accessToken信息:{}",responseStr);

        ObjectMapper objectMapper = new ObjectMapper();
        AccessGrant accessGrant = null;
        try {
            Map map = objectMapper.readValue(responseStr, Map.class);
            accessGrant = new AccessGrant((String)map.get(""),(String)map.get(""),(String)map.get(""),2L);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        return accessGrant;
    }


    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }
}
