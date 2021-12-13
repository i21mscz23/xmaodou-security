package com.xmd.social.config;

import com.xmd.processor.SocialAuthenticationFilterPostProcessor;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/11/17 下午2:01
 */
public class CustomSocialConfigurer extends SpringSocialConfigurer {

    /**
     * 过滤器处理请求的地址
     */
    private String filterProcessesUrl;

    /**
     * 过滤器处理器
     */
    private SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor;

    public CustomSocialConfigurer(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    /**
     *
     * @param object
     * @param <T> SocialAuthenticationFilter
     * @return
     */
    @Override
    protected <T> T postProcess(T object) {

        //修改拦截地址
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);

        if(socialAuthenticationFilterPostProcessor != null){
            socialAuthenticationFilterPostProcessor.process(filter);
        }

        return (T) filter;
    }

    public String getFilterProcessesUrl() {
        return filterProcessesUrl;
    }

    public void setFilterProcessesUrl(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    public SocialAuthenticationFilterPostProcessor getSocialAuthenticationFilterPostProcessor() {
        return socialAuthenticationFilterPostProcessor;
    }

    public void setSocialAuthenticationFilterPostProcessor(SocialAuthenticationFilterPostProcessor socialAuthenticationFilterPostProcessor) {
        this.socialAuthenticationFilterPostProcessor = socialAuthenticationFilterPostProcessor;
    }
}
