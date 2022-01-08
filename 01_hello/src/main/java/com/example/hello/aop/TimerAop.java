package com.example.hello.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
//Bean과 Component 차이 @Bean은 클래스에 붙일수 없고 메서드만 가능
//@Configuration 하나의 클래스에 여러개의 Bean이 등록됨
public class TimerAop {

    @Pointcut("execution(* com.example.hello.controller..*.*(..))")
    private void timerCut(){}

    //package하위에있는 Timer Annotation이 설정된 것만 적용
    @Pointcut("@annotation(com.example.hello.annotation.Timer)")
    private void enableTimer(){

    }


    @Around("timerCut() && enableTimer()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result  = joinPoint.proceed();

        stopWatch.stop();
        System.out.println("total time: "+stopWatch.getTotalTimeSeconds());
    }

}
