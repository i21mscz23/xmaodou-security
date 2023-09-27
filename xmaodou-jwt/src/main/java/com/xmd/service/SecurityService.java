package com.xmd.service;

import com.xmd.authentication.JwtAuthenticationToken;
import com.xmd.user.JwtUserDetails;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/9/8 下午8:42
 */
public interface SecurityService {

    String obtainUsername(String username);
    String obtainPassword(String password);

    /**
     * 通过用户名查询信息，返回UserDetail用作校验
     * @param username
     * @return
     */
    JwtUserDetails check(String username);

    /**
     * 验证token
     * @param token
     * @param sign
     * @return
     */
    JwtAuthenticationToken checkToke(String token, String sign);

    /**
     * 登陆成功后封装返回信息
     * @param authentication
     * @return
     */
    Object onAuthenticationSuccess(Authentication authentication);


    /**
     * 登陆失败后封装返回信息
     * @param exception
     * @return
     */
    Object onAuthenticationFailure(AuthenticationException exception);


    /**
     * 封装登陆后请求权限驳回
     * @param e
     * @return
     */
    Object accessDenied(AccessDeniedException e);

    /**
     * 封装匿名权限拒绝（无token、token无效）
     * @param e
     * @return
     */
    Object anonymityDenied(AuthenticationException e);


    /**
     * 请求权限校验
     * @param userDetails
     * @param request
     * @return
     */
    boolean hasPermission(JwtUserDetails userDetails, HttpServletRequest request);

    /**
     * 密码加密
     * @param password
     * @return
     */
    String getPassword(String password);
}
