package com.example.server.controller;

import com.example.server.dto.User;
import com.example.server.dto.VariableRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class ServerApiController {

    @GetMapping("/hello")
    public String hello() {
        return "hello server";
    }


    @GetMapping("/hello2")
    public User hello2(@RequestParam String name, @RequestParam int age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }

    @PostMapping("/user/{userId}/name/{userName}")
    public User post(@RequestBody User user, @PathVariable int userId, @PathVariable String userName) {
        log.info("userId : {}, userName : {}", userId, userName);
        log.info("client req : {}", user);
        return user;
    }


    @PostMapping("/userex/{userId}/name/{userName}")
    public User post2(@RequestBody User user
            , @PathVariable int userId
            , @PathVariable String userName
            , @RequestHeader("x-authorization") String authorization
            , @RequestHeader("custom-header") String customHeader) {
        log.info("userId : {}, userName : {}", userId, userName);
        log.info("authorization : {}, customheader: {}", authorization, customHeader);
        log.info("client req : {}", user);
        return user;
    }

    @PostMapping("/userexvar/{userId}/name/{userName}")
    public VariableRequest<User> post3(
//            HttpEntity<String> entity //순수 String 나옴 -> String 값 다확인
             @RequestBody VariableRequest<User> user
            , @PathVariable int userId
            , @PathVariable String userName
            , @RequestHeader("x-authorization") String authorization
            , @RequestHeader("custom-header") String customHeader) {
//        log.info("req : {}", entity.getBody() );
        log.info("userId : {}, userName : {}", userId, userName);
        log.info("authorization : {}, customheader: {}", authorization, customHeader);
        log.info("client req : {}", user);
        VariableRequest<User> response = new VariableRequest<>();
        response.setHeader(
                new VariableRequest.Header()
        );

        response.setResBody(
                user.getResBody()
        );

        return response;
    }


}
