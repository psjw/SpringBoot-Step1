package com.example.hello.dto;


import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.util.List;

public class ValidListUserRequestDto {
    private String name;
    @Max(value = 90)
    private int age;
    @Valid //@Valid 없으면 Validation 검사 안함
    private List<Car> cars;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "ValidListUserRequestDto{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", cars=" + cars +
                '}';
    }
}
