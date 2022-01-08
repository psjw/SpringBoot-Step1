package com.example.hello.ioc.refactoring;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * packageName : com.example.hello.ioc
 * fileName : UrlEncoder
 * author : psjw
 * date : 2022-01-06
 * description :
 * ===========================================================
 * DATE              AUTHOR          NOTE
 * -----------------------------------------------------------
 * 2022-01-06        psjw         최초 생성
 */
public class UrlEncoder implements IEncoder{
    public String encode(String message){
        try {
            return URLEncoder.encode(message, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
