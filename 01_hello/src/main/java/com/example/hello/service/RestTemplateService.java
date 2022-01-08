package com.example.hello.service;

import com.example.hello.dto.RestTemplateUserRequest;
import com.example.hello.dto.RestTemplateUserResponse;
import com.example.hello.dto.VariableRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {

    /*
    {
        name:"steve",
        age:10
    }
     */

    //http://localhost/api/server/hello
    //resposne
    public String hello() {
        URI uri = UriComponentsBuilder.fromUriString("http://localhost:8080")
                .path("/api/server/hello")
                .encode() //URL Encoding
                .build()
                .toUri();

        System.out.println(uri.toString());
        //원래는 RestTemplate Pool 만들어서 써야됨
        RestTemplate restTemplate = new RestTemplate();
        //get 은 HttpMethod의 GET
        String result = restTemplate.getForObject(uri, String.class);

        ResponseEntity<String> result1 = restTemplate.getForEntity(uri, String.class);
        System.out.println(result1.getStatusCode());
        System.out.println(result1.getBody());


//        return result;
        return result1.getBody();
    }


        /*
    {
        name:"steve",
        age:10
    }
     */

    //http://localhost/api/server/hello2
    //resposne
    public RestTemplateUserResponse hello2() {
        URI uri = UriComponentsBuilder.fromUriString("http://localhost:8080")
                .path("/api/server/hello2")
                .queryParam("name", "aaa") // http://localhost:8080/api/server/hello2?name=steve&age=10
                .queryParam("age", 102)
                .encode() //URL Encoding
                .build()
                .toUri();

        System.out.println(uri.toString());
        //원래는 RestTemplate Pool 만들어서 써야됨
        RestTemplate restTemplate = new RestTemplate();
        //get 은 HttpMethod의 GET
        RestTemplateUserResponse result = restTemplate.getForObject(uri, RestTemplateUserResponse.class);
        System.out.println(result);

        ResponseEntity<RestTemplateUserResponse> result1 = restTemplate.getForEntity(uri, RestTemplateUserResponse.class);
        System.out.println(result1.getStatusCode());
        System.out.println(result1.getBody());


//        return result;
        return result1.getBody();
    }

    
    //Post 전송
    public RestTemplateUserResponse post() {
        //http://localhost:8080/api/server/user/{userId}/name/{userName}
        URI uri = UriComponentsBuilder.
                fromUriString("http://localhost:8080")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                //.buildAndExpand()//로 {} 매핑가능
                .build()
                .expand(100,"steve") //순서대로 {}랑 매핑
                .toUri();
        System.out.println("uri = " + uri);

        //http body -> object -> object mapper -> json -> rest template -> http body json
        RestTemplateUserRequest req = new RestTemplateUserRequest();
        req.setName("steve");
        req.setAge(10);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RestTemplateUserResponse> response
                = restTemplate.postForEntity(uri, req, RestTemplateUserResponse.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());

        return response.getBody();
    }



    //Post 전송 Header 지정
    public void post2() {
        //http://localhost:8080/api/server/user/{userId}/name/{userName}
        URI uri = UriComponentsBuilder.
                fromUriString("http://localhost:8080")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                //.buildAndExpand()//로 {} 매핑가능
                .build()
                .expand(100,"steve") //순서대로 {}랑 매핑
                .toUri();
        System.out.println("uri = " + uri);

        //http body -> object -> object mapper -> json -> rest template -> http body json
        RestTemplateUserRequest req = new RestTemplateUserRequest();
        req.setName("steve");
        req.setAge(10);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response
                = restTemplate.postForEntity(uri, req, String.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());

    }

    //exchange()

    public RestTemplateUserResponse exchange(){
        //http://localhost:8080/api/server/user/{userId}/name/{userName}
        URI uri = UriComponentsBuilder.
                fromUriString("http://localhost:8080")
                .path("/api/server/userex/{userId}/name/{userName}")
                .encode()
                //.buildAndExpand()//로 {} 매핑가능
                .build()
                .expand(100,"steve") //순서대로 {}랑 매핑
                .toUri();
        System.out.println("uri = " + uri);

        //http body -> object -> object mapper -> json -> rest template -> http body json
        //RequestEntity
        RestTemplateUserRequest req = new RestTemplateUserRequest();
        req.setName("steve");
        req.setAge(10);

        RequestEntity<RestTemplateUserRequest> requestEntity
                = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header", "ffff")
                .body(req);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RestTemplateUserResponse> response
                = restTemplate.exchange(requestEntity, RestTemplateUserResponse.class);

        return response.getBody();
    }

    public RestTemplateUserResponse genericExchange(){
        //http://localhost:8080/api/server/user/{userId}/name/{userName}
        URI uri = UriComponentsBuilder.
                fromUriString("http://localhost:8080")
                .path("/api/server/userexvar/{userId}/name/{userName}")
                .encode()
                //.buildAndExpand()//로 {} 매핑가능
                .build()
                .expand(100,"steve") //순서대로 {}랑 매핑
                .toUri();
        System.out.println("uri = " + uri);

        //http body -> object -> object mapper -> json -> rest template -> http body json
        //RequestEntity
        RestTemplateUserRequest userReq = new RestTemplateUserRequest();
        userReq.setName("steve");
        userReq.setAge(10);

        VariableRequest<RestTemplateUserRequest> req
                = new VariableRequest<RestTemplateUserRequest>();
        req.setHeader(
            new VariableRequest.Header()
        );

        req.setResBody(
                userReq
        );

        RequestEntity<VariableRequest<RestTemplateUserRequest>> requestEntity
                = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header", "ffff")
                .body(req);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<VariableRequest<RestTemplateUserResponse>> response
                = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<>(){});
                // 오류발생 VariableRequest<RestTemplateUserResponse> 제네릭은 class 사용 못함
//                =restTemplate.exchange(requestEntity, VariableRequest<RestTemplateUserResponse>.class)
        //response.getBody() -> ResponseEntity
        //response.getBody().getResBody() -> VariableRequest의 body
        return response.getBody().getResBody();
    }
}

