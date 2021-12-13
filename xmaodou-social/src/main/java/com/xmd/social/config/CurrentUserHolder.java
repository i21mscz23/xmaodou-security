package com.xmd.social.config;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.UserIdSource;

/**
 * @Description
 * @Author lixiao
 * @Date 2020/5/23 16:27
 */
public class CurrentUserHolder implements UserIdSource {

    @Override
    public String getUserId() {
        String userId = null;
        SecurityContext context = SecurityContextHolder.getContext();
        if(context != null) {
            Object principal = context.getAuthentication().getPrincipal();
            if(principal instanceof UserDetails) {
                userId = ((UserDetails)principal).getUsername();
            }
        }
        if(StringUtils.isBlank(userId)) {
            userId = RandomStringUtils.randomNumeric(4);
        }
        return userId;
    }
}
