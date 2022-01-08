package com.example.hello.controller;


import com.example.hello.dto.SwaggerUserRequestDto;
import com.example.hello.dto.SwaggerUserResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"API 정보를 제공하는 Controller"})
@RestController
@RequestMapping("/api/swagger")
public class SwaggerApiController {


    //Spring boot 2.6.x Swagger 사용 불가
    //다운그레이드 해줌 2.5.8
    //Swagger 3.xx 부터는 무조건 모두 공개됨
    //http://localhost:9090/swagger-ui/  맨뒤 / 무조건 해줘야됨
    @GetMapping("/hello")
    public String hello() {
        return "swagger hello";
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "x", value = "x값", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "y", value = "y값", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/plus/{x}")
    public int plus(
            //@ApiParam(value = "x값")
            @PathVariable int x,
            //@ApiParam(value = "y값") //변수가 많으면 불편 메소드 자체에 지정
            @RequestParam int y) {
        return x + y;
    }


    @ApiResponse(code = 502, message = "사용자 나이가 10살 이하일때")
    @ApiOperation(value = "사용자의 이름과 나이를 echo하는 메소드")
    @GetMapping("/user")
    public SwaggerUserResponse user(SwaggerUserRequestDto userReq) {
        return new SwaggerUserResponse(userReq.getName(), userReq.getAge());
    }


    @PostMapping("/user")
    public SwaggerUserResponse userPost(@RequestBody SwaggerUserRequestDto userReq) {
        return new SwaggerUserResponse(userReq.getName(), userReq.getAge());
    }
}
