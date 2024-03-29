package com.xmd.social.qq.connect;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/11/15 下午3:39
 */
public class QQOAuth2Template extends OAuth2Template {

    private String redirectUri;

    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl,String redirectUri) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        this.redirectUri = redirectUri;
        setUseParametersForClientAuthentication(true);
    }


    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        if(StringUtils.isNotBlank(redirectUri)){
            parameters.remove("redirect_uri");
            parameters.add("redirect_uri",redirectUri);
        }

        String responseStr = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);


        String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(responseStr, "&");

        String accessToken = StringUtils.substringAfterLast(items[0], "=");
        Long expiresIn = Long.valueOf(StringUtils.substringAfterLast(items[1], "="));;

        String refreshToken = StringUtils.substringAfterLast(items[2], "=");

        return new AccessGrant(accessToken, null, refreshToken, expiresIn);
    }


    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }


    @Override
    public String buildAuthorizeUrl(OAuth2Parameters parameters) {
        if(StringUtils.isNotBlank(redirectUri)){
            parameters.remove("redirect_uri");
            parameters.setRedirectUri(redirectUri+ "/callback.do");
        }
        return buildAuthenticateUrl(parameters);
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }


}
