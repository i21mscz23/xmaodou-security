package com.xmd.handler.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xmd.user.JwtUserDetails;
import com.xmd.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.AuthorityUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/9/8 下午2:22
 */
public class JwtHandler {

    private static Logger logger = LoggerFactory.getLogger(JwtHandler.class);

    /**
     * 创建jwt
     * @param params
     * @param sign
     * @param expireTime
     * @return
     */
    public String createJwt(Map<String,Object> params, String sign, LocalDateTime expireTime){


        JWTCreator.Builder builder = JWT.create();
        builder.withIssuedAt(new Date()).withExpiresAt(TimeUtils.convertToDate(expireTime));
        if(params != null){
            builder.withPayload(params);
        }
        return builder.sign(Algorithm.HMAC256(sign));
    }

    /**
     * 判断jwt是否合法
     * @param jwt
     * @param sign
     * @return
     */
    public boolean decryptJwt(String jwt,String sign){
        // 带入密钥解密
        JWTVerifier require = JWT.require(Algorithm.HMAC256(sign)).build();
        try {
            DecodedJWT verify = require.verify(jwt);
        }catch (Exception e){
            logger.error("token校验失败，jwt:{}",jwt);
            return false;
        }

        return true;
    }



    /**
     * 获取过期时间
     * @param jwt
     * @param sign
     * @return
     */
    public LocalDateTime expiresTime(String jwt,String sign)  {
        JWTVerifier require = JWT.require(Algorithm.HMAC256(sign)).build();

        LocalDateTime time = null;
        try {
            DecodedJWT verify = require.verify(jwt);
            Date date = verify.getExpiresAt();
            if(date != null){
                time = TimeUtils.convertToLocalDateTime(date);
            }
        }catch (Exception e){
            logger.error("token校验失败，jwt:{}",jwt);
        }
        return time;


    }

    /**
     * 获取用户信息
     * @param jwt
     * @param sign
     * @return
     */
    public JwtUserDetails userDetails(String jwt,String sign){
        JWTVerifier require = JWT.require(Algorithm.HMAC256(sign)).build();
        DecodedJWT verify = require.verify(jwt);
        Map<String, Claim> claims = verify.getClaims();

        JwtUserDetails userDetails = null;
        if(claims != null){
            String username = claims.get("username").asString();
            userDetails = new JwtUserDetails(username,null,null, AuthorityUtils.createAuthorityList("ROLE_ADMIN"));
            userDetails.setAdmin(claims.get("isAdmin").asBoolean());
        }

        return userDetails;
    }







}
