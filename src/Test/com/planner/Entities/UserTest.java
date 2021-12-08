package com.planner.Entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User user;

    @BeforeEach
    void setUp() {
        user = new User("Tom", "1@1.com", "1234");
    }

    @Test
    void getName() {
        assertEquals("Tom", user.getName());
    }

    @Test
    void getEmail() {
        assertEquals("1@1.com", user.getEmail());
    }

    @Test
    void getPassword() {
        assertEquals("1234", user.getPassword());
    }

    @Test
    void setPassword() {
        user.setPassword("4321");
        assertEquals("4321", user.getPassword());
    }

    @Test
    void setEmail() {
        user.setEmail("2@2.com");
        assertEquals("2@2.com", user.getEmail());
    }
}