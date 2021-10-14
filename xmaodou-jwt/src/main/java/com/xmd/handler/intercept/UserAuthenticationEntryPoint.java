package com.xmd.handler.intercept;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmd.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 当匿名登陆情况下，权限驳回（token无效、无token）
 * @Author lixiao
 * @Date 2020/5/18 13:56
 */
public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
//        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        Object result = securityService.anonymityDenied(e);
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
