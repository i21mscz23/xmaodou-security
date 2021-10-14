package com.xmd.authentication;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Description
 * @Author lixiao
 * @Date 2020/5/16 14:30
 */
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) authentication;

        UserDetails userDetails = userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());

        if (userDetails == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }

        check(authentication.getCredentials(),userDetails.getPassword());

        JwtAuthenticationToken authenticationResult = new JwtAuthenticationToken(userDetails,authenticationToken.getCredentials(), userDetails.getAuthorities());

        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;
    }

    /**
     * 密码匹配检测
     * @param credentials
     * @param password
     */
    private void check(Object credentials, String password) {
        if(StringUtils.isBlank(password)){
            throw new BadCredentialsException("用户不存在");
        }
        String pwd = (String) credentials;
        if(!passwordEncoder.matches(pwd,password)){
            throw new BadCredentialsException("密码错误");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
