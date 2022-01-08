package com.example.hello.controller;

import com.example.hello.dto.ValidCustomUserRequestDto;
import com.example.hello.dto.ValidListUserRequestDto;
import com.example.hello.dto.ValidUserRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ValidApiController {

    @PostMapping("/user")
    public ResponseEntity user(@RequestBody ValidUserRequestDto user){
        System.out.println(user);
        //이전 방식
        if(user.getAge() >= 90){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(user);
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/uservalid")
    public ResponseEntity userValid(@Valid @RequestBody ValidUserRequestDto user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder sb= new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError field = (FieldError)objectError;
                String message = objectError.getDefaultMessage();
                System.out.println("field = " + field.getField());
                System.out.println("message = " + message);
                sb.append("field : " + field.getField()+"\n");
                sb.append("message : " + message+"\n");
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }

        //logic

        System.out.println(user);
        return ResponseEntity.ok(user);
    }

/*
{
  "name":"홍길동",
  "age" :70,
  "email":"test@test.com",
  "phoneNumber": "010-1111-2222",
  "reqYearMonth": "201111"

}
 */
    //CustomValid
    @PostMapping("/usercustomvalid")
    public ResponseEntity userValid(@Valid @RequestBody ValidCustomUserRequestDto user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder sb= new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError field = (FieldError)objectError;
                String message = objectError.getDefaultMessage();
                System.out.println("field = " + field.getField());
                System.out.println("message = " + message);
                sb.append("field : " + field.getField()+"\n");
                sb.append("message : " + message+"\n");
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }
        //logic
        System.out.println(user);
        return ResponseEntity.ok(user);
    }


    //listValid
    @PostMapping("/userlistvalid")
    public ResponseEntity userListValid(@Valid @RequestBody ValidListUserRequestDto user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            StringBuilder sb= new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError field = (FieldError)objectError;
                String message = objectError.getDefaultMessage();
                System.out.println("field = " + field.getField());
                System.out.println("message = " + message);
                sb.append("field : " + field.getField()+"\n");
                sb.append("message : " + message+"\n");
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }
        //logic
        System.out.println(user);
        return ResponseEntity.ok(user);
    }
}
