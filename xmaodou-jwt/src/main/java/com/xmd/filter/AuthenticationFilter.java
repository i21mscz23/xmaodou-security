package com.xmd.filter;


import com.xmd.authentication.JwtAuthenticationToken;
import com.xmd.service.SecurityService;
import com.xmd.user.SecurityConst;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 过滤器校验token是否合法
 * @Author lixiao
 * @Date 2020/5/16 16:50
 */
public class AuthenticationFilter extends OncePerRequestFilter {

    private SecurityService securityService;

    private String sign;

    public AuthenticationFilter(SecurityService securityService,String jwtSecret) {
        this.securityService = securityService;
        this.sign = jwtSecret;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader("Authorization");
        if(StringUtils.isBlank(token)){
            token = httpServletRequest.getParameter("Authorization");
        }

        if (StringUtils.isBlank(token)){
            //其他过滤器判断请求是否合法
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }

        if(!StringUtils.startsWithIgnoreCase(token,SecurityConst.TOKEN_TYPE)){
            throw new BadCredentialsException("token格式错误");
        }

        //解析jwt,通过秘钥判断是否被修改过
        String jwt = StringUtils.substringAfter(token, SecurityConst.TOKEN_TYPE);
        JwtAuthenticationToken jwtAuthenticationToken = securityService.checkToke(jwt, sign);

        SecurityContextHolder.getContext().setAuthentication(jwtAuthenticationToken);

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
