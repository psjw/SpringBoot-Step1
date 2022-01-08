package com.example.hello.controller;

import com.example.hello.dto.ResponseUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ResponseApiController {

    //Response Text 형태
    @GetMapping("/text")
    public String text(@RequestParam String account){
        return account;
    }

    /*
    {
        "name":"steve",
        "age" : 10,
        "phoneNumber": "010-1111-2222",
        "address":"패스트 캠퍼스"
     }
     */
    
    //Response JSON
    // request -> object mapper -> object 변환 -> controller method -> return -> objectmapper->json->response
    @PostMapping("/json")
    public ResponseUser json(@RequestBody ResponseUser user){
        return user; //200ok
    }

    
    //Response ResponseEntity
    @PutMapping("/response_put")
    public ResponseEntity<?> put(@RequestBody ResponseUser responseUser){
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }

}
