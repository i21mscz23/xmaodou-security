package com.xmd.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/12/24 下午4:00
 */
@RestController
@RequestMapping("/oauth")
public class OAuth2Controller {

    /**
     * 与
     * @EnableGlobalMethodSecurity(prePostEnabled = true)
     * 组合使用
     * @return
     */
    @GetMapping("/oauthScope")
    @PreAuthorize("#oauth2.hasScope('write')")
    public String oauthScope(){
        System.out.println("oauthScope");
        return "oauthScope";
    }

    @GetMapping("/me")
    public Object me(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }
}
