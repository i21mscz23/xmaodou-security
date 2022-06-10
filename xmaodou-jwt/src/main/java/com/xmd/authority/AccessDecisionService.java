package com.xmd.authority;

import com.xmd.service.SecurityService;
import com.xmd.user.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @Author lixiao
 * @Date 2020/5/18 13:14
 */
public class AccessDecisionService implements PermissionService{

    @Autowired
    private SecurityService securityService;

    public boolean hasPermission(HttpServletRequest request, Authentication auth) {
        Object principal = auth.getPrincipal();
        if(principal instanceof JwtUserDetails){
            //针对请求地址。需要进行权限判断
            JwtUserDetails jwtUserDetails = (JwtUserDetails) principal;
            return securityService.hasPermission(jwtUserDetails,request);
        }else {
            return false;
        }
    }

}
