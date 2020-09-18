package com.wenba.annotation;

import java.lang.annotation.*;

/**
 * @description:
 * @author: tongrongbing
 * @date: 2020-08-13 13:52
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {

    String userType() default "";
}
