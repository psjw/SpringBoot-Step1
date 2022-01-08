package com.example.hello.controller;


import com.example.hello.dto.ResponseUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ResponsePageController {

    @RequestMapping("/main")
    public String main() {
        return "main.html";
    }

    //ResponseEntity

    @ResponseBody
    @GetMapping("/user")
    public ResponseUser user() {
        var user = new ResponseUser();
        user.setName("steve");
        user.setAddress("서울");
        return user;
    }
}
