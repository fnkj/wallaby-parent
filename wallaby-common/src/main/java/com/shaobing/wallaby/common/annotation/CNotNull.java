package com.shaobing.wallaby.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段非空注解
 *
 * @author luyb@servyou.com.cn
 * @version 2016-09-11 9:08
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CNotNull {
}
