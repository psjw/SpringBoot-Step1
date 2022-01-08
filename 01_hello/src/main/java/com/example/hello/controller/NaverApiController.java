package com.example.hello.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/api/server")
public class NaverApiController {

    //https://openapi.naver.com/v1/search/blog.json\
    // ?query=%EA%B0%88%EB%B9%84%EC%A7%91
    // &display=10
    // &start=1
    // &sort=sim
    @GetMapping("/naver")
    public String naver(){
        System.out.println("@@@");
        String query = "갈비집";
        //String encode = Base64.getEncoder().encodeToString(query.getBytes(StandardCharsets.UTF_8));

        URI uri = UriComponentsBuilder.fromUriString("https://openapi.naver.com")
                .path("/v1/search/blog.json")
                .queryParam("query", query)
                .queryParam("display", 10)
                .queryParam("start", 1)
                .queryParam("sort", "sim")
                .encode()
                .build().toUri();
        System.out.println(uri.toString());

        RequestEntity<Void> requestEntity = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", "C7APrn0XsB5tqDyEV_cb")
                .header("X-Naver-Client-Secret", "AOaatm3xWP")
                .build();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> exchange = restTemplate.exchange(requestEntity, String.class);

        System.out.println(exchange.getBody());
        return exchange.getBody();
    }
}
