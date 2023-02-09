package com.xmd.processor;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.web.ConnectInterceptor;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.WebRequest;

import java.util.Iterator;

public class UrlConnectInterceptor implements ConnectInterceptor {
    @Override
    public void preConnect(ConnectionFactory connectionFactory, MultiValueMap parameters, WebRequest request) {

    }

    @Override
    public void postConnect(Connection connection, WebRequest request) {
        Iterator<String> parameterNames = request.getParameterNames();
        System.out.println();
    }
}
