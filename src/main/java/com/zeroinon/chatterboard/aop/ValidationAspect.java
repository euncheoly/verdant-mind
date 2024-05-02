package com.zeroinon.chatterboard.aop;

import com.zeroinon.chatterboard.exception.GeneralException;
import com.zeroinon.chatterboard.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class ValidationAspect {



    private final JwtService jwtService;


    public ValidationAspect(JwtService jwtService) {
        this.jwtService = jwtService;
    }


    @Pointcut("@annotation(tokenValidator)")
    public void callAt(TokenValidator tokenValidator) {}


//    @Before("@annotation(com.zeroinon.chatterboard.aop.TokenValidator)")
    @Before("callAt(tokenValidator)")
    public void validateJwt(JoinPoint joinPoint, TokenValidator tokenValidator) throws Throwable {

        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
        String accessToken  = request.getHeader("Authorization");
        String token = accessToken.replace("Bearer ", "");

        if(!jwtService.isValidEveryoneToken(token)){
            throw new GeneralException.InvalidToken("Invalid token");
        }

        switch (tokenValidator.userLevel()){
            case ANYONE :
                if(!jwtService.isValidEveryoneToken(token)){
                    throw new GeneralException.InvalidToken("Invalid token");
                }
                break;
            case ADMIN_ONLY :
                if(!jwtService.isValidAdminToken(token)){
                    log.error("Unauthorized access token");
                    throw new GeneralException.InvalidToken("Invalid token");
                }
                break;
            default:
                log.error("[undefined method level]:"+ joinPoint.getSignature().getName());
        }
    }





}


