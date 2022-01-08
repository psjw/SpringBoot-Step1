package com.example.hello.object_mapper;


import com.example.hello.dto.Car;
import com.example.hello.dto.ObjectMapperUser2;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Arrays;
import java.util.List;

public class ObjectMapperMain {
    public static void main(String[] args) throws JsonProcessingException {
        System.out.println("main");
        ObjectMapper objectMapper = new ObjectMapper();

        ObjectMapperUser2 user = new ObjectMapperUser2();
        user.setName("홍길동");
        user.setAge(10);

        Car car1 = new Car();
        car1.setName("K5");
        car1.setCarNubmer("11가 1111");
        car1.setType("sedan");


        Car car2 = new Car();
        car2.setName("Q5");
        car2.setCarNubmer("22가 2222");
        car2.setType("SUV");


        List<Car> cars = Arrays.asList(car1, car2);
        user.setCars(cars);

        System.out.println(user);

        String json = objectMapper.writeValueAsString(user);
        System.out.println("json = " + json);


        //JSON Parsing -> JSON 값 변경
        //AOP or Filter, Interceptor 등에서 사용 -> 파라미터 변경
        //노드를 직접 다룸
        JsonNode jsonNode = objectMapper.readTree(json);

        String _name = jsonNode.get("name").asText();
        int _age = jsonNode.get("age").asInt();

        System.out.println("name : "+_name);
        System.out.println("age : "+_age);

        //json의 새로운 배열의 node
        String _list = jsonNode.get("cars").asText();
        System.out.println("_list = " + _list);

        JsonNode carArr = jsonNode.get("cars");
        ArrayNode arrayNode = (ArrayNode) carArr;
        List<Car> _cars = objectMapper.convertValue(arrayNode, new TypeReference<>() {});
        System.out.println("_cars = " + _cars);

        //JSONNode는 값이 안바뀜 ObjectNode에서 해줘야됨
        ObjectNode objectNode = (ObjectNode) jsonNode;
        objectNode.put("name", "steve");
        objectNode.put("age", 20);
        System.out.println("objectNode.toPrettyString() = " + objectNode.toPrettyString());
    }
}
