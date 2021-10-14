package com.xmd.service.impl;

import cn.hutool.json.JSONUtil;
import com.google.common.collect.Maps;
import com.xmd.handler.jwt.JwtHandler;
import com.xmd.properties.JwtProperties;
import com.xmd.response.ServerResponse;
import com.xmd.service.SecurityService;
import com.xmd.user.JwtUserDetails;
import com.xmd.user.SecurityConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/9/8 下午8:45
 */
public class DefaultSecurityService implements SecurityService {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private JwtHandler jwtHandler;

    @Override
    public JwtUserDetails check(String username) {
        //默认登陆，账号随意，密码123908
        return new JwtUserDetails(username,"$2a$10$49CiAqKl.7DcuCViWCjNO.qCcPgui6eycalYkXtjQHNR0B7zdlXkK",
                jwtProperties.getJwtExpiration(), AuthorityUtils.createAuthorityList("ROLE_ADMIN"),null);
    }

    @Override
    public Object onAuthenticationSuccess(Authentication authentication) {
        JwtUserDetails principal = (JwtUserDetails) authentication.getPrincipal();

        Map<String,Object> params = JSONUtil.parse(principal).toBean(HashMap.class);
        params.remove("password");

        Long expirationTime = principal.getExpirationTime();

        LocalDateTime expireTime = LocalDateTime.now().plusSeconds(expirationTime);
        String jwt = jwtHandler.createJwt(params, jwtProperties.getJwtSecret(), expireTime);

        Map<String,Object> result = Maps.newHashMap();
        result.put("access_token",jwt);
        result.put("token_type", SecurityConst.TOKEN_TYPE);
        result.put("expires_in", expirationTime);
        return ServerResponse.createBySuccessData(result);
    }

    @Override
    public Object onAuthenticationFailure(AuthenticationException exception) {
        String message = exception.getMessage();
        return ServerResponse.createByErrorCodeMessage(message,40001);
    }

    @Override
    public Object accessDenied(AccessDeniedException e) {
        return ServerResponse.createByErrorCodeMessage("权限拦截，必要时请联系管理员",40002);
    }

    @Override
    public Object anonymityDenied(AuthenticationException e) {
        return ServerResponse.createByErrorCodeMessage("用户验证失败",40003);
    }

    @Override
    public boolean hasPermission(JwtUserDetails userDetails, HttpServletRequest request) {
        return true;
    }
}
