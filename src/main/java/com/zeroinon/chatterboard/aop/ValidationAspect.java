package com.zeroinon.chatterboard.aop;

import com.zeroinon.chatterboard.exception.GeneralException;
import com.zeroinon.chatterboard.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
public class ValidationAspect {

    private final JwtService jwtService;


    public ValidationAspect(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Before("@annotation(com.zeroinon.chatterboard.aop.TokenValidator)")
    public void validateJwt(JoinPoint joinPoint) throws Throwable {

        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
        String accessToken  = request.getHeader("Authorization");
        String token = accessToken.replace("Bearer ", "");

        if(!jwtService.isValid(token)){
            throw new GeneralException.InvalidToken("Invalid token");
        }


    }





}


