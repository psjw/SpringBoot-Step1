package com.example.restaurant.naver;


import com.example.restaurant.naver.dto.SearchImageReq;
import com.example.restaurant.naver.dto.SearchImageRes;
import com.example.restaurant.naver.dto.SearchLocalReq;
import com.example.restaurant.naver.dto.SearchLocalRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class NaverClient {

    @Value("${naver.client.id}")
    private String naverClientId;

    @Value("${naver.client.secret}")
    private String naverSecret;

    @Value("${naver.url.search.local}")
    private String naverLocalSearchUrl;

    @Value("${naver.url.search.image}")
    private String naverImageUrl;


    public SearchLocalRes searchLocal(SearchLocalReq searchLocalReq) {
        System.out.println(searchLocalReq);
        var uri = UriComponentsBuilder
                .fromUriString(naverLocalSearchUrl)
                .queryParams(searchLocalReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();
        System.out.println(uri);
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverSecret);


        var responseType = new ParameterizedTypeReference<SearchLocalRes>() {
        };
        var httpEntity = new HttpEntity<>(headers);

        var responseEntity
                = new RestTemplate().exchange(uri, HttpMethod.GET, httpEntity, responseType);

        return responseEntity.getBody();
    }

    public SearchImageRes searchImage(SearchImageReq searchImageReq) {
        URI uri = UriComponentsBuilder
                .fromUriString(naverImageUrl)
                .queryParams(searchImageReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        var headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverSecret);
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);

        var responseType = new ParameterizedTypeReference<SearchImageRes>() {
        };

        var httpEntity = new HttpEntity<>(headers);

        var responseEntity = new RestTemplate()
                .exchange(uri, HttpMethod.GET, httpEntity, responseType);



        return responseEntity.getBody();
    }

}
