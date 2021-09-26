package com.xmd.authority;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author lixiao
 * @Date 2020/5/18 13:22
 */
public interface PermissionService {

    /**
     * 细粒度权限控制
     * @param request
     * @param authentication
     * @return
     */
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
