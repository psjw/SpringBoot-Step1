package com.example.hello.controller;

import com.example.hello.dto.FilterUserRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j //logback
@RestController
@RequestMapping("/api")
public class FilterApiController {



    @PostMapping("/filteruser")
    public FilterUserRequestDto user(@RequestBody FilterUserRequestDto user){
        log.info("User : {}",user);

        return user;
    }
}
