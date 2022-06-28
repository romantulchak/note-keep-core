package com.notekeep.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class AuthControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void signIn() {
        System.out.println(mvc);
    }

    @Test
    void shouldCreateUserWithoutFirstNameAndLastName() throws Exception {
        String body = """
                {
                   "email": "test@gmail.com",
                   "password": "1111"
                }
                """;
        mvc.perform(MockMvcRequestBuilders.post("/api/auth/sign-up")
                        .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreateUserWithFirstNameAndLastName() throws Exception {
        String body = """
                {
                   "email": "test@gmail.com",
                   "password": "1111",
                   "firstName": "Test",
                   "lastName": "Test"
                }
                """;
        mvc.perform(MockMvcRequestBuilders.post("/api/auth/sign-up")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
