package com.example.hello.config;

import com.example.hello.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.Executor;

@RequiredArgsConstructor
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    //@Autowired 순환참조 막기 위해 생성자 주입
    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authInterceptor);
        //addPathPatterns() 주소추가 , excludePathPatterns()주소 제외
        registry.addInterceptor(authInterceptor).addPathPatterns("/api/private/*"); //특정 URL만 검사
        //추가시 순서대로 Interceptor 실행
//        registry.addInterceptor()
    }


    //Async 관련 설정
    @Bean("async-thread")
    public Executor asyncThread(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();

        //최초 Pool 10개를 다쓰면 Queue에 내용이 들어감. Queue도 10개 다차면 Pool Size 10개만큼 한번더 늘어남 20개됨
        //20개를 쓰다가 다차면 Queue에 또 10개가 참
        threadPoolTaskExecutor.setMaxPoolSize(100); //
        threadPoolTaskExecutor.setCorePoolSize(10); //
        threadPoolTaskExecutor.setQueueCapacity(10); //
        threadPoolTaskExecutor.setThreadNamePrefix("Async-");
        return threadPoolTaskExecutor;

    }
}
