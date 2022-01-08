package com.example.hello;

import com.example.hello.ioc_spring.ApplicationContextProvider;
import com.example.hello.ioc_spring.Base64EncdoerSpringDi;
import com.example.hello.ioc_spring.EncoderSpringDi;
import com.example.hello.ioc_spring.UrlEncoderSpringDi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
//Filter를 특정컨트롤러에만 걸기
@ServletComponentScan
@EnableAsync //Async사용시 필요
public class HelloApplication {

    public static void main(String[] args) {

        SpringApplication.run(HelloApplication.class, args);

        ApplicationContext context = ApplicationContextProvider.getContext();




        Base64EncdoerSpringDi base64Encoder = context.getBean(Base64EncdoerSpringDi.class);


        EncoderSpringDi encoder = new EncoderSpringDi(base64Encoder);
        String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";
        String result = encoder.encode(url);
        System.out.println(result);


        UrlEncoderSpringDi urlEncoder = context.getBean(UrlEncoderSpringDi.class);
        //SetMethod로 주입
        encoder.setiEncoder(urlEncoder);
        result = encoder.encode(url);
        System.out.println(result);


        System.out.println("============================================");
        //@Qulifier로 처리 했을경우

//        EncoderSpringDi encoder1 = context.getBean(EncoderSpringDi.class);
//        result = encoder1.encode(url);
//        System.out.println(result);

        System.out.println("============================================");
        //@Config로 처리하여 다중 bean등록
        EncoderSpringDi encoder2 = context.getBean("base64Encode", EncoderSpringDi.class);
        result = encoder2.encode(url);
        System.out.println(result);

    }



}

//여러개의 빈을 사용
@Configuration
class AppConfig{
    @Bean("base64Encode")
    public EncoderSpringDi encoder(Base64EncdoerSpringDi base64Encoder){
        return new EncoderSpringDi(base64Encoder);
    }

    @Bean("urlEncode")
    public EncoderSpringDi encoder(UrlEncoderSpringDi base64Encoder){
        return new EncoderSpringDi(base64Encoder);
    }
}
