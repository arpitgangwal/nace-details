package com.nacedetails.demo.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nacedetails.demo.dto.NaceEntity;
import com.nacedetails.demo.repository.NaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
@SpringBootTest
class NaceServiceImplTest {
    @MockBean
    private NaceRepository naceRepository;

    @Autowired
    private NaceService naceService;
   private ObjectMapper objectMapper;
   private ClassPathResource resource;
    private List<NaceEntity> naceEntityList;
 // Mock the service layer
    @BeforeEach
    void setUp() throws IOException {
        objectMapper = new ObjectMapper();
        // Load the JSON file from the classpath
        resource = new ClassPathResource("input.json");
           naceEntityList = objectMapper.readValue(resource.getInputStream(),new TypeReference<List<NaceEntity>>() {});

    }
    @Test
    void putNaceDetails() throws IOException {

       when(naceRepository.saveAll(naceEntityList)).thenReturn(naceEntityList);
       List<NaceEntity> result =  naceService.putNaceDetails(naceEntityList);
       assertEquals(2, result.size());
        assertEquals(ArrayList.class, result.getClass());
    }

    @Test
    void getNaceDetails() throws IOException {


        when(naceRepository.findById(anyLong())).thenReturn(Optional.ofNullable(naceEntityList.get(0)));
        NaceEntity naceEntity = naceService.getNaceDetails(123443l);
        assertNotEquals(null, naceEntity);
    }
}