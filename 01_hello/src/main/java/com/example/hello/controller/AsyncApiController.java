package com.example.hello.controller;


import com.example.hello.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("")
public class AsyncApiController {

    private final AsyncService asyncService;


    public AsyncApiController(AsyncService asyncService) {
        this.asyncService = asyncService;
    }


/*    @GetMapping("/api/async")
    public String hello(){
        asyncService.hello();
        //@Async Annotation이 없으면 asyncService.hello() 끝날때까지 대기
        // 존재하면 바로 종료
        //[nio-9090-exec-1] Thread실행
        log.info("method end");
        return "hello";
    }*/


    @GetMapping("/api/async")
    public CompletableFuture hello(){
        log.info("completable future init");

        //nio-9090-exec-1 에서 최초 응답
        //task-1 종료후 응답 : async hello
        return asyncService.run();
    }
}
