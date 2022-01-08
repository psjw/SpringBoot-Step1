package com.example.hello.ioc_spring;


import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Component
public class UrlEncoderSpringDi implements IEncoderSpringDi{
    public String encode(String message)  {
        try {
            return URLEncoder.encode(message, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
