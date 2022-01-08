package com.example.hello.aop;


import com.example.hello.dto.AopRequestUser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

@Aspect
@Component
public class DecodeAop {

    @Pointcut("execution(* com.example.hello.controller..*.*(..))")
    private void decodeCut() {
    }

    //package하위에있는 Timer Annotation이 설정된 것만 적용
    @Pointcut("@annotation(com.example.hello.annotation.Decode)")
    private void enableDecode() {
    }

    @Before("decodeCut()&&enableDecode()")
    public void before(JoinPoint joinPoint) throws UnsupportedEncodingException {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof AopRequestUser) {
                AopRequestUser user = AopRequestUser.class.cast(arg);
                String base64Email = user.getEmail();
                String email = new String(Base64.getDecoder().decode(base64Email),"UTF-8");
                user.setEmail(email);
            }
        }
    }

    @AfterReturning(value = "decodeCut()&&enableDecode()", returning = "returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj) {
        if(returnObj instanceof AopRequestUser){
            AopRequestUser user = AopRequestUser.class.cast(returnObj);
            String email = user.getEmail();
            String base64Email = Base64.getEncoder().encodeToString(email.getBytes());
            user.setEmail(base64Email);
        }
    }
}
