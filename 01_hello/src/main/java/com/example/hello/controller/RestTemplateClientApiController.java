package com.example.hello.controller;


import com.example.hello.dto.RestTemplateUserResponse;
import com.example.hello.service.RestTemplateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class RestTemplateClientApiController {

    private final RestTemplateService restTemplateService;

    public RestTemplateClientApiController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    //단순문자
    @GetMapping("/hello")
    public String getHello(){
        return restTemplateService.hello();
    }

    
    //객체
    @GetMapping("/hello2")
    public RestTemplateUserResponse getHello2(){
        return restTemplateService.hello2();
    }


    //post
    @GetMapping("/hello3")
    public RestTemplateUserResponse getHello3(){
        return restTemplateService.post();
    }


    //post string
    @GetMapping("/hello4")
    public RestTemplateUserResponse getHello4(){
        restTemplateService.post2();
        return new RestTemplateUserResponse();
    }

    //post exchange
    @GetMapping("/hello5")
    public RestTemplateUserResponse getHello5(){
        return restTemplateService.exchange();
    }

    /*
    {
        "header" : {
            "responseCode" : "OK"
        },
        "body" : {
            내용이 가변
        }
    }
     */

    //post exchange 변하는 JSON Body
    @GetMapping("/hello6")
    public RestTemplateUserResponse getHello6(){
        return restTemplateService.genericExchange();
    }
}
