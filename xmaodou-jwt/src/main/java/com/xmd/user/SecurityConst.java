package com.xmd.user;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/9/8 下午3:39
 */
public interface SecurityConst {


    /**
     * 用户登陆地址
     */
    public final static String LOGIN_URL = "/jwt/login";

    /**
     * 提示登录页面地址
     */
    public final static String LOGIN_PAGE = "/require";

    /**
     * token类型 前缀部分
     */
    public final static String TOKEN_TYPE = "Bearer ";

    /**
     * jwt秘钥
     */
    public final static String JWT_SECRET = "123456";

    /**
     * jwt过期时间（单位秒）
     */
    public final static Long JWT_EXPIRATION_TIME = 259200L;
}
