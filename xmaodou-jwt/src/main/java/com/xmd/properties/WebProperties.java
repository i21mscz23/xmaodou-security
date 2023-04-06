package com.xmd.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "com.xmd.web")
public class WebProperties {

    private List<String> ignoring;


    public List<String> getIgnoring() {
        return ignoring;
    }

    public void setIgnoring(List<String> ignoring) {
        this.ignoring = ignoring;
    }
}
