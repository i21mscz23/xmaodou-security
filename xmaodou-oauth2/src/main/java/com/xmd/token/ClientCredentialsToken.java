package com.xmd.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.util.Assert;

import java.util.Collection;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/12/29 下午4:24
 */
public class ClientCredentialsToken extends AbstractAuthenticationToken {

    private Object credentials;

    public ClientCredentialsToken(Object credentials) {
        super((Collection)null);
        this.credentials = credentials;
        this.setAuthenticated(true);
    }


    public Object getCredentials() {
        return this.credentials;
    }

    public Object getPrincipal() {
        return null;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        Assert.isTrue(!isAuthenticated, "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        super.setAuthenticated(false);
    }

    public void eraseCredentials() {
        super.eraseCredentials();
        this.credentials = null;
    }


}
