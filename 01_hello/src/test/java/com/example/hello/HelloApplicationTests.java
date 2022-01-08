package com.example.hello;

import com.example.hello.dto.ObjectMapperUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HelloApplicationTests {

    @Test
    void contextLoads() throws JsonProcessingException {
        System.out.println("---------------");

        //Text JSON  -> Object
        //Object -> Text JSON

        //Controller Request JSON(text) -> Object
        // Response Object -> JSON(text)

        var objectMapper = new ObjectMapper();

        // object -> text
        var user = new ObjectMapperUser("steve", 10, "010-1111-2222");

        var text = objectMapper.writeValueAsString(user); //GetterMethod 필요
        System.out.println("text = " + text);

        // text -> object
        var objectUser = objectMapper.readValue(text, ObjectMapperUser.class); //디폴트 생성자 필요
        System.out.println("objectUser = " + objectUser);


    }

}
