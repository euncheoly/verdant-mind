package com.zeroinon.chatterboard.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TokenValidator {

    UserLevel userLevel() default UserLevel.ANYONE;

    enum UserLevel {
        ADMIN_ONLY,
        ANYONE
    }


}




