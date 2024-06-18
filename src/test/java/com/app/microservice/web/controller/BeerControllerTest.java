package com.app.microservice.web.controller;

import com.app.microservice.web.model.BeerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getBeerById() {
        try {
            mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void saveNewBeer() {
        try {
        BeerDto beerDto = BeerDto.builder().build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);
        mockMvc.perform(post("/api/v1/beer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void updateBeerById() {
        try {
            BeerDto beerDto = BeerDto.builder().build();
            String beerDtoJson = objectMapper.writeValueAsString(beerDto);
            mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(beerDtoJson))
                    .andExpect(status().isNoContent());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}