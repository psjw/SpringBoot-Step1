package com.example.hello.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

/**
 * packageName : com.example.hello.dto
 * fileName : Car
 * author : psjw
 * date : 2022-01-06
 * description :
 * ===========================================================
 * DATE              AUTHOR          NOTE
 * -----------------------------------------------------------
 * 2022-01-06        psjw         최초 생성
 */
public class Car {
    @NotBlank
    private String name;
    @NotBlank
    @JsonProperty("car_number")
    private String carNubmer;
    @NotBlank
    @JsonProperty("TYPE")
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarNubmer() {
        return carNubmer;
    }

    public void setCarNubmer(String carNubmer) {
        this.carNubmer = carNubmer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", carNubmer='" + carNubmer + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
