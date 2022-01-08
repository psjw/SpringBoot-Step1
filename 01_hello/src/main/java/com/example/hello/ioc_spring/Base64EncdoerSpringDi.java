package com.example.hello.ioc_spring;


import org.springframework.stereotype.Component;

import java.util.Base64;

@Component("base64") //Spring Bean 등록 -> @Qualifier에서  호출할 이름 변경(base64)
public class Base64EncdoerSpringDi implements IEncoderSpringDi{
    public String encode(String message){
        return Base64.getEncoder().encodeToString(message.getBytes());
    }
}
