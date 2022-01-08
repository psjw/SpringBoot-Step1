package com.example.hello.ioc.refactoring;

import java.util.Base64;

/**
 * packageName : com.example.hello.ioc
 * fileName : Base64Encoder
 * author : psjw
 * date : 2022-01-06
 * description :
 * ===========================================================
 * DATE              AUTHOR          NOTE
 * -----------------------------------------------------------
 * 2022-01-06        psjw         최초 생성
 */
public class Base64Encoder implements IEncoder{

    public String encode(String message){
        return Base64.getEncoder().encodeToString(message.getBytes());
    }

}
