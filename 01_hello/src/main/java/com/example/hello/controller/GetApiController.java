package com.example.hello.controller;


import com.example.hello.dto.GetUserRequestDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    @GetMapping(path = "/hello") // http://localhost:9090/api/get/hello
    public String hello() {
        return "get Hello";
    }

    // @RequestMapping만 사용시 get/post/put/delete 모둔 메서드 매팽
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    //get http://localhost:9090/api/get/hi
    public String hi() {
        return "hi";
    }

    @GetMapping("/path-variable/{name}")
    //http://localhost:9090/api/get/path-variable/{name}
    public String pathVariable(@PathVariable(name = "name") String pathName) {
        System.out.println("PathVariable : " + pathName);
        return pathName;
    }

    //쿼리파라미터
    //http://localhost:9090/api/get/query-param?user=steve&email=steve@gmail.com
    @GetMapping(path = "query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam) {
        StringBuilder sb = new StringBuilder();
        queryParam.entrySet().forEach(entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");
            sb.append(entry.getKey() + "=" + entry.getValue() + "\n");
        });
        return sb.toString();
    }

    @GetMapping("query-param02")
    public String queryParam02(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam int age
    ) {
        System.out.println(name);
        System.out.println(email);
        System.out.println(age);
        return name + " " + email + " " + age;
    }

    @GetMapping("query-param03")
    public String queryParam03(GetUserRequestDto userRequest) {
        System.out.println(userRequest);
        return userRequest.toString();
    }
}
