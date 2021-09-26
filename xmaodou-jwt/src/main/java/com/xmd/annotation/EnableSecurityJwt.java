package com.xmd.annotation;

import java.lang.annotation.*;

/**
 * @Description
 * @Author lixiao
 * @Date 2021/9/26 上午9:25
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EnableSecurityJwt {
}
