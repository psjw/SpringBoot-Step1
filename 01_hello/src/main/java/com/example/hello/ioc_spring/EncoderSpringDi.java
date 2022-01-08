package com.example.hello.ioc_spring;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//Bean에 대해서 하나만 있으면 바로 매칭이 되지만 -> Base64, Url 두개이기 때문에 스프링이 알수 없음
//-> @Qualifier("base64Encoder")
public class EncoderSpringDi {
    private IEncoderSpringDi iEncoder;


    //@Qualifier("urlEncoderSpringDi") -> 디폴트값은 클래스의 앞글자만 소문자 UrlEncoderSpringDi -> urlEncoderSpringDi
    public EncoderSpringDi( //@Qualifier("urlEncoderSpringDi")
    @Qualifier("base64") IEncoderSpringDi iEncoder){
        this.iEncoder = iEncoder;
    }

    public void setiEncoder(IEncoderSpringDi iEncoder) {
        this.iEncoder = iEncoder;
    }

    public String encode(String message){
        return iEncoder.encode(message);
    }
}
