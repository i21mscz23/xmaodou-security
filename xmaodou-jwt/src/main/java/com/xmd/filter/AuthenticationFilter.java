package com.xmd.filter;


import com.xmd.authentication.JwtAuthenticationToken;
import com.xmd.handler.jwt.JwtHandler;
import com.xmd.user.JwtUserDetails;
import com.xmd.user.SecurityConst;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

/**
 * @Description 过滤器校验token是否合法
 * @Author lixiao
 * @Date 2020/5/16 16:50
 */
public class AuthenticationFilter extends OncePerRequestFilter {

    private JwtHandler jwtHandler;

    private String sign;

    public AuthenticationFilter(JwtHandler jwtHandler,String jwtSecret) {
        this.jwtHandler = jwtHandler;
        this.sign = jwtSecret;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = httpServletRequest.getHeader("Authorization");
        if (StringUtils.isBlank(token)){
            //其他过滤器判断请求是否合法
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }

        if(!StringUtils.startsWith(token,SecurityConst.TOKEN_TYPE)){
            throw new BadCredentialsException("token格式错误");
        }

        //解析jwt,通过秘钥判断是否被修改过
        String jwt = StringUtils.substringAfter(token, SecurityConst.TOKEN_TYPE);

        LocalDateTime expiresTime = jwtHandler.expiresTime(jwt, sign);
        if(expiresTime == null || LocalDateTime.now().isAfter(expiresTime)){
            throw new BadCredentialsException("token无效");
        }

        JwtUserDetails jwtUserDetails = jwtHandler.userDetails(jwt, sign);

        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(jwtUserDetails,"", Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        jwtAuthenticationToken.setDetails(new WebAuthenticationDetails(httpServletRequest));
        SecurityContextHolder.getContext().setAuthentication(jwtAuthenticationToken);

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
