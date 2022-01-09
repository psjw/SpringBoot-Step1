package com.psjw.jpa.bookmanager.domain;

import org.junit.jupiter.api.Test;

class UserTest{
    @Test
    void test(){
        User user = new User();
        user.setName("test@test.com");
        user.setName("test");

        System.out.println(user);
    }

}