package com.xmd.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SocialAuthenticationToken;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.function.ServerResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @Author lixiao
 * @Date 2020/5/25 16:31
 */
@Component("socialAuthenticationSuccessHandler")
public class SocialAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;



    @Autowired
    private JdbcTemplate jdbcTemplate;



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SocialAuthenticationToken token = (SocialAuthenticationToken) authentication;
        SocialUserDetails principal = (SocialUserDetails) token.getPrincipal();
        List<String> localUserIds = jdbcTemplate.queryForList("select userId from " + "social_" + "UserConnection where providerId = ? and providerUserId = ?", String.class, token.getProviderId(), principal.getUsername());
        localUserIds.remove(principal.getUsername());
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=UTF-8");
        if(CollectionUtils.isEmpty(localUserIds)){
            response.getWriter().write(objectMapper.writeValueAsString("需要注册绑定"));
        }else {
            response.getWriter().write(objectMapper.writeValueAsString("模拟这个是一个token"));
        }


    }
}
