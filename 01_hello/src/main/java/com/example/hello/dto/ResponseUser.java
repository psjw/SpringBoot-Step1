package com.example.hello.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


//@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
//특정 필드만 사용
//NULL인 필드 제거 ->JsonInclude
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseUser {
    private String name;
    private Integer age;
    @JsonProperty("phone_number")
    private String phoneNumber;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "ResponseUser{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
