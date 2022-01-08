package com.example.hello.ioc.refactoring2;


import com.example.hello.ioc.refactoring.IEncoder;
import com.example.hello.ioc.refactoring.UrlEncoder;

public class Encoder {

    private IEncoder iEncoder;

    public Encoder() {
//        this.iEncoder = new Base64Encoder();
        this.iEncoder = new UrlEncoder();
    }

    public String encode(String message)
    {
        return iEncoder.encode(message);
    }
}
