package com.example.hello.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class ObjectMapperUser {
    private String name;
    private int age;

    @JsonProperty("phone_number")
    private String phoneNumber;

    public ObjectMapperUser() {
        this.name = null;
        this.age=0;
        this.phoneNumber = null;
    }

    public ObjectMapperUser(String name, int age, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    //    public ObjectMapperUser getDefaultUser(){
    //objectMapper 메소드가 참조하는 메서드는 get붙이면 안됨
    public ObjectMapperUser defaultUser(){
        return new ObjectMapperUser("defalut", 0,"010-1111-3333");
    }

    @Override
    public String toString() {
        return "ObjectMapperUser{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
