package com.nacedetails.demo.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nacedetails.demo.dto.NaceEntity;
import com.nacedetails.demo.service.NaceService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class NaceDetailsControllerTest {
    @Autowired
    private MockMvc mockMvc; // MockMvc instance for testing
    ObjectMapper objectMapper;
    ClassPathResource resource;
    List<NaceEntity> naceEntityList;
    @MockBean
    private NaceService naceService; // Mock the service layer
    @BeforeEach
    void setUp() throws IOException {
         objectMapper = new ObjectMapper();
        // Load the JSON file from the classpath
         resource = new ClassPathResource("input.json");
        naceEntityList = objectMapper.readValue(resource.getInputStream(),new TypeReference<List<NaceEntity>>() {});

    }


    @Test
    void putNaceDetails() throws Exception {

        // Use the ObjectMapper to parse the JSON file into an object
        when(naceService.putNaceDetails(naceEntityList)).thenReturn(naceEntityList);

        // Perform the GET request and verify the response
        mockMvc.perform(put("/v1/nace/").contentType(MediaType.APPLICATION_JSON).content(resource.getInputStream().readAllBytes()))
                .andExpect(status().isCreated());
    }

    @Test
    void putNaceDetailsInternalServerError() throws Exception {

        // Use the ObjectMapper to parse the JSON file into an object
        when(naceService.putNaceDetails(naceEntityList)).thenThrow(RuntimeException.class);

        // Perform the GET request and verify the response
        mockMvc.perform(put("/v1/nace/").contentType(MediaType.APPLICATION_JSON).content(resource.getInputStream().readAllBytes()))
                .andExpect(jsonPath("$.cause").value("Internal Server Error"))  .andExpect(status().is5xxServerError());
    }


    @Test
    void getNaceDetails() throws Exception {

        when(naceService.getNaceDetails(398481L)).thenReturn( naceEntityList.get(0));

        // Perform the GET request and verify the response
        mockMvc.perform(get("/v1/nace/retrieve/398481"))
                .andExpect(jsonPath("$.naceId").value(398481)).andExpect(jsonPath("$.level").value("1")).andExpect(status().isOk());
    }
    @Test
    void getNaceDetailsNotFound() throws Exception {

        when(naceService.getNaceDetails(398481L)).thenThrow(EntityNotFoundException.class);

        // Perform the GET request and verify the response
        mockMvc.perform(get("/v1/nace/retrieve/398481"))
                .andExpect(jsonPath("$.cause").value("Nace not found")).andExpect(status().isNotFound());
    }
}