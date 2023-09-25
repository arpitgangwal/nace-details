package com.nacedetails.demo.controller;

import com.nacedetails.demo.dto.NaceEntity;
import com.nacedetails.demo.service.NaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/nace")

public class NaceDetailsController
{
    private final NaceService naceService;

    @Autowired
    public NaceDetailsController(NaceService naceService){
        this.naceService = naceService;
    }
    @PutMapping (path ="/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<NaceEntity>> putNaceDetails(@RequestBody List<NaceEntity> naceEntityList) {
       return new ResponseEntity<>(naceService.putNaceDetails(naceEntityList), HttpStatus.CREATED);
    }

    @GetMapping(path = "/retrieve/{naceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NaceEntity> getNaceDetails(@PathVariable("naceId") long id){
        return new ResponseEntity<>(naceService.getNaceDetails(id), HttpStatus.OK);

    }
}
