package com.shaobing.wallaby.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 非线程安全注解
 *
 * @author luyb@servyou.com.cn
 * @version 2016-09-12 12:42
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UnThreadSafe {
}
