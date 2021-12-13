package com.xmd.processor;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * @Description
 * @Author lixiao
 * @Date 2020/5/25 16:34
 */
public interface SocialAuthenticationFilterPostProcessor {

    void process(SocialAuthenticationFilter socialAuthenticationFilter);
}
