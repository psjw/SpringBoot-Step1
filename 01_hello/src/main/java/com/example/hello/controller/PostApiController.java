package com.example.hello.controller;

import com.example.hello.dto.PostRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostApiController {
    /*
    JSON
    string : value ""
    number: value
    boolean : value
    object : value  {}
    array : value []

    {
        "phone_number" : "010-1111-2222",
        "age" : 10,
        isAgree : false,
        "account" : {
            "email" : "steve@gamil.com",
            "password" : "1234"
        }
    }

    //user 조회 하는 경우
    {
        "user_list": [
            {
                "account" : "abcd",
                "password" : "1234"
            },
            {
                "account" : "aaaa",
                "password" : "1111"
            },
            {
                "account" : "aaaabbb",
                "password" : "1111222"
            }
        ]
    }


    //예제
    {
        "account" : "",
        "email" : "",
        "password" : "",
        "address" : ""
    }

     */


    @PostMapping("/post")
    //Body전송 @RequestBody 붙여야됨
    public void post(@RequestBody Map<String, Object> requestData){

        requestData.forEach((key, value) -> {
            System.out.println("key : " + key);
            System.out.println("value : " + value);
        });
    }


    @PostMapping("/post02")
    //Body전송 @RequestBody 붙여야됨
    public void post02(@RequestBody PostRequestDto postRequestDto){
        System.out.println(postRequestDto);
    }
}
