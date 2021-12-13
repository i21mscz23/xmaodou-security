package com.xmd.social.wechat.api;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/12/13 下午4:35
 */
public interface Weixin {

    WeixinUserInfo getUserInfo(String openId);
}
