package com.xmd.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/9/8 下午3:08
 */
public class JwtUserDetails implements UserDetails {

    /**
     * 用户编号
     */
    private String userId;

    private String username;

    /**
     * 数据库查到的密码
     */
    private String password;

    private Long expirationTime;

    private Long loginTime;

    /**
     * 是否为admin
     */
    private boolean isAdmin;



    @JsonIgnore
    private List<GrantedAuthority> authorities;

    public JwtUserDetails() {
    }

    public JwtUserDetails(String username, String password, Long expirationTime, List<GrantedAuthority> authorities,String userId){
        this.username = username;
        this.password = password;
        this.expirationTime = expirationTime;
        this.authorities = authorities;
        this.loginTime = System.currentTimeMillis();
        this.userId = userId;

    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public Long getExpirationTime() {
        return expirationTime;
    }

    @Override
    public boolean isAccountNonExpired() {
        long now = System.currentTimeMillis();
        if((now - this.loginTime) > this.expirationTime){
            return false;
        }
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
