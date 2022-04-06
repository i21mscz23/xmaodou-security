package com.xmd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author lixiao
 * @Date 2020/5/23 22:04
 */
@Component
public class SocialJdbcUserDetailService implements SocialUserDetailsService {


    @Autowired
    private SocialService socialService;

    @Override
    public SocialUserDetails loadUserByUserId(String providerUserId) throws UsernameNotFoundException {

        return socialService.check(providerUserId);
    }
}
