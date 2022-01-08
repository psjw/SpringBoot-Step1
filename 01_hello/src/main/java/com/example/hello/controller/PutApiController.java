package com.example.hello.controller;

import com.example.hello.dto.PutRequestDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PutApiController {

    /*
    {
        "name" : "steve",
        "age" : 20,
        "car_list" : [
            {
                "name" : "BMW",
                "carNumber": "11가 1234"
            },
            {
                "name" : "A4",
                "carNumber": "22가 3456"
            }
        ]
    }

     */

    @PutMapping("/put")
    public PutRequestDto put(@RequestBody PutRequestDto putRequestDto){
        System.out.println(putRequestDto);
        return putRequestDto;
    }

    @PutMapping("/put/{userId}")
    public PutRequestDto put(@RequestBody PutRequestDto putRequestDto, @PathVariable(name = "userId") Long id){
        System.out.println(id);
        return putRequestDto;
    }
}
