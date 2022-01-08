package com.example.hello.filter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@Component
@WebFilter(urlPatterns = "/api/filteruser/*") //특정 url에만 Filter 적용
public class GlobalFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request
            , ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //전처리
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        //ContentCachingRequestWrapper의 cachedContent에 Size 저장해놓음
        // -> 후처리 이후에 읽음 writeToCache가동작됨
        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse) response);



//        BufferedReader br = httpServletRequest.getReader();
        //Error ->java.lang.IllegalStateException: getReader() has already been called for this request
        //Stream은 Cursor단위로 읽게 되고 하단의 코드에서 Cursor가 제일 끝으로 이동하여 읽을께 없어서 에러남 
        //Controller에서 JSON Body를 읽을수 없음
//        br.lines().forEach(
//                line -> {
//                    log.info("url : {},line : {}", url, line);
//                }
//        );

        chain.doFilter(httpServletRequest, httpServletResponse);
        String url = httpServletRequest.getRequestURI();
        //후처리 
        //req
        String reqContent = new String(httpServletRequest.getContentAsByteArray());

        log.info("request url : {}, request body : {}", url, reqContent);

        String resContent = new String(httpServletResponse.getContentAsByteArray());

        int httpStatus = httpServletResponse.getStatus();
        //httpServletResponse.getContentAsByteArray()에서 모두 읽어버리기 떄문에 Client Response Body가 없음 -> 다시 복사해놓음
        httpServletResponse.copyBodyToResponse();
        log.info("response status : {}, responseBody : {}", httpStatus, resContent);
    }
}
