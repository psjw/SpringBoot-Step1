package com.example.hello.controller;

import com.example.hello.dto.ExceptionRequestUserDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/api")
@Validated //RequestParam valid체크
public class ExceptionApiController {
    // ?name =1234
    @GetMapping("/except")
    //@RequestParam(required = false) String name ->필수값아님
    public ExceptionRequestUserDto getExcept(
            @Size(min = 2)
            @RequestParam String name,
            @NotNull
            @Min(1)
            @RequestParam Integer age){
        ExceptionRequestUserDto user = new ExceptionRequestUserDto();
        user.setName(name);
        user.setAge(age);

        int a = 10 + age;
        return user;

    }

    @PostMapping("/except")
    public ExceptionRequestUserDto postExcept(@Valid @RequestBody ExceptionRequestUserDto user){
        return user;
    }
    
    //특정 컨트롤러에서만 동작 -> ControllerAdvice에서도 가능
//    @ExceptionHandler(value= MethodArgumentNotValidException.class)
//    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e){
//        System.out.println("api controller");
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//    }
}
