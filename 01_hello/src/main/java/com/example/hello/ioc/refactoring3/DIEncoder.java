package com.example.hello.ioc.refactoring3;


import com.example.hello.ioc.refactoring.IEncoder;

public class DIEncoder {

    private IEncoder iEncoder;

    public DIEncoder(IEncoder iEncoder) {
//        this.iEncoder = new Base64Encoder();
//        this.iEncoder = new UrlEncoder();
        this.iEncoder = iEncoder;
    }

    public String encode(String message)
    {
        return iEncoder.encode(message);
    }
}
