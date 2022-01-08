package com.example.hello.ioc;


import com.example.hello.ioc.ori.Base64EncoderOri;
import com.example.hello.ioc.ori.UrlEncoderOri;
import com.example.hello.ioc.refactoring.Base64Encoder;
import com.example.hello.ioc.refactoring.IEncoder;
import com.example.hello.ioc.refactoring.UrlEncoder;
import com.example.hello.ioc.refactoring2.Encoder;
import com.example.hello.ioc.refactoring3.DIEncoder;

public class IocMain {
    public static void main(String[] args) {

        String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";


        System.out.println("======================Step1=========================");
        //1. Base 64 Encoding (최초 조건)
        // URL Encoding 추가
        Base64EncoderOri encoder = new Base64EncoderOri();
        String result = encoder.encode(url);
        System.out.println("result = " + result);

        UrlEncoderOri urlEncoder = new UrlEncoderOri();
        String urlResult = urlEncoder.encode(url);
        System.out.println("urlResult = " + urlResult);


        System.out.println("======================Step2=========================");
        //-> 무의미 하게 계속 추가 -> 커스터마이징 필요
        //인터페이스 정의 -> 기존 코드와 거의 동일
        IEncoder encoder1 = new Base64Encoder();
        String result1 = encoder1.encode(url);
        System.out.println("result1 = " + result1);

        IEncoder urlEncoder1 = new UrlEncoder();
        String urlResult1 = urlEncoder1.encode(url);
        System.out.println("urlResult1 = " + urlResult1);


        System.out.println("======================Step3=========================");
        //DI 적용
        //Encoder생성자 코드 계속 수정해야됨
        Encoder encoder2 = new Encoder();
        String result2 = encoder2.encode(url);
        System.out.println("result2 = " + result2);


        System.out.println("======================Step4=========================");
        //DI 다시 적용
        //코드 수정 필요없음
        DIEncoder encoder3 = new DIEncoder(new Base64Encoder());
        String result3 = encoder3.encode(url);
        System.out.println("result3 = " + result3);

        DIEncoder urlEncoder3 = new DIEncoder(new UrlEncoder());
        String urlResult3 = urlEncoder3.encode(url);
        System.out.println("urlResult3 = " + urlResult3);
        
        //암호화 추가 -> 기존 소스는 변경할 필요없음
        
        //-> new 객체() 부분 제거 -> Spring Container가 관리


    }
}
