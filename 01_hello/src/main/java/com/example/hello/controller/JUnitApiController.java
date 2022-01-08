package com.example.hello.controller;

import com.example.hello.dto.JUnitRequestDto;
import com.example.hello.dto.JUnitResponse;
import com.example.hello.junit.componet.Calculator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class JUnitApiController {

    private final Calculator calculator;

    @GetMapping("/sum")
    public int sum(@RequestParam int x, @RequestParam int y){
        return calculator.sum(x, y);
    }

    @PostMapping("/minus")
    public JUnitResponse minus(@RequestBody JUnitRequestDto req){
        int result = calculator.minus(req.getX(), req.getY());
        JUnitResponse response = new JUnitResponse();
        response.setResult(result);
        response.setResponse(new JUnitResponse.Body());
        return response;
    }
}
