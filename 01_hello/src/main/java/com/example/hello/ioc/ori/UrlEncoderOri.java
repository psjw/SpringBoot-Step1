package com.example.hello.ioc.ori;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlEncoderOri {
    public String encode(String message){
        try {
            return URLEncoder.encode(message, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
