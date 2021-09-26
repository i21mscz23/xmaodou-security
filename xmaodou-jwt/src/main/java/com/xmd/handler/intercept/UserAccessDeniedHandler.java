package com.xmd.handler.intercept;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmd.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 当token有效时，权限拒绝是回调
 * @Author lixiao
 * @Date 2020/5/18 14:00
 */
public class UserAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        Object result = securityService.accessDenied(e);

        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
