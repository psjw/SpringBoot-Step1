package com.example.hello.ioc.ori;

import java.util.Base64;

public class Base64EncoderOri {
    public String encode(String message){
        return Base64.getEncoder().encodeToString(message.getBytes());
    }
}
