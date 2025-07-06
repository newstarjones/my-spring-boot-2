package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HelloController.class)
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @org.junit.jupiter.api.Test
    void getUserById() throws Exception {
        mockMvc.perform(get("/hello/1"))
                .andExpect(status().isOk());
//                .andExpect("")
//                .andExpect(jsonPath("$.name").value("Tom"));
    }
}