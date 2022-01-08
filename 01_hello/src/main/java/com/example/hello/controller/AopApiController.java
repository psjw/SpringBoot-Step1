package com.example.hello.controller;

import com.example.hello.annotation.Decode;
import com.example.hello.annotation.Timer;
import com.example.hello.dto.AopRequestUser;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AopApiController {

    @GetMapping("/getaop/{id}")
    public String getAop(@PathVariable Long id, @RequestParam String name){
        System.out.println("get method");
        System.out.println("get method : "+id);
        System.out.println("get method : "+name);
        return id + " " + name;
    }

/*
{
  "id" :"steve",
  "pw" : "1234",
  "email" :"steve@test.com"
}
 */
    @PostMapping("/postaop")
    public AopRequestUser postAop(@RequestBody AopRequestUser user){
        System.out.println("post method : "+user);
        return user;
    }


    @Timer
    @DeleteMapping("/deleteaop")
    public void deleteAop() throws InterruptedException {
        //db logic 시간
        Thread.sleep(2000);
    }

/*
{
  "id" :"steve",
  "pw" : "1234",
  "email" :"c3RldmVAZ2FtaWwuY29t"
}
 */

    @Decode
    @PutMapping("/putaop")
    public AopRequestUser putAop(@RequestBody AopRequestUser user){
        System.out.println("put method : "+user);
        return user;
    }
}
