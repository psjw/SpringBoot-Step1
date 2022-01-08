package com.example.hello.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class AsyncService {
    /*
    @Async
    public void hello() {
        for(int i =0; i<10;i++){
            try {
                Thread.sleep(2000);
                //Task-1 쓰레드 작성
                log.info("thread sleep...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    */

    
    //여러개의 API를 전송한 다음 결과를 Join해서 받을때 사용
    //기본적 스프링 쓰레드가 8개 정도여서 직접 스레드를 만들어줘야댐
    @Async("async-thread") //Poo Size  증가 -> AppConfig.java
    //@Async는 AOP 기반이기 때문에 public에만 가능
    //NoSQL은 Spring Webflux를 사용하는게 낫다
    //RDB는 의미 없음 -> 전체적 flow가 Async로 동작할수 없음
    public CompletableFuture run(){
        //hello()  같은 클래스에서 같은 메서드 호출하면 동작안함
        return new AsyncResult(hello()).completable();
    }

    public String hello() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(2000);
                //Task-1 쓰레드 작성
                log.info("thread sleep...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        return "aync hello";
    }
}
