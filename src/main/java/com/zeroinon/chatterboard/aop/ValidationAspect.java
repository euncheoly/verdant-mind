package com.zeroinon.chatterboard.aop;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
public class ValidationAspect {


    @Before("@annotation(com.zeroinon.chatterboard.aop.TokenValidator)")
    public void validateJwt(JoinPoint joinPoint) throws Throwable {

        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
        String accessToken  = request.getHeader("Authorization");
        System.out.println("accessToken : "+accessToken);
        System.out.println("#@@@@@@@@@@@@@@@@@@@@@@@######## jwt validation true ##########");

    }





}


