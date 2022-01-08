package com.example.hello.interceptor;


import com.example.hello.annotation.Auth;
import com.example.hello.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //Spring Context에 저장
        //if Filter에서 ContentCachingRequestWrapper에 넣어주었다면 (ContentCachingRequestWrapper)request 형변환을 통해 body를 또 읽을수 있음
        String url = request.getRequestURI();
        URI uri = UriComponentsBuilder
                .fromUriString(request.getRequestURI()) //주소까지만
                .query(request.getQueryString()) //쿼리까지
                .build().toUri();

        log.info("request url : {}", url);
        boolean hasAnnotation = checkAnnotation(handler, Auth.class);
        log.info("hasAnnotation : {}",hasAnnotation);
        //나의 서버는 모두 public 으로 동작하는데
        //단! Auth 권한을 가진 요청에 대해서는 세션, 쿠키 등 체크
        if(hasAnnotation){
            //권한 체크
            String query = uri.getQuery();

            log.info(query);
            if(query.equals("name=steve")){
                //예외를 던진다.
                return true;
            }
            throw new AuthException();
//            return false;

        }


        //return false 이면 컨트롤러까지 가지 않는다.
        return true;
    }

    //class 예약어 못써서 clazz 많이 씀
    private boolean checkAnnotation(Object handler, Class clazz) {
        //resource  javascript, html은 통과
        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }
        // annotation check
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        if (null != handlerMethod.getMethodAnnotation(clazz) || null != handlerMethod.getBeanType().getAnnotation(clazz)) {
            // Auth annotaion이 있을때는 true
            return true;
        }

        return false;
    }
}
