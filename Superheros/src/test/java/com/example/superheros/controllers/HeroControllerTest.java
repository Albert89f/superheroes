package com.example.superheros.controllers;

import com.example.superheros.repositories.SuperheroRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HeroControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    SuperheroRepository superheroRepository;

    @Test
    @WithMockUser(username = "test", password = "test")
    void createHero() throws Exception {
        mockMvc.perform(put("/hero/new").param("name", "testhero")
                )
                .andExpect((status().isCreated())).andExpect(jsonPath(
                        "id").value("1")).andExpect(jsonPath(
                        "name").value("testhero"));

        superheroRepository.deleteAll();
    }
}