package com.nacedetails.demo.controller;

import com.nacedetails.demo.dto.NaceEntity;
import com.nacedetails.demo.service.NaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/v1/nace")
@RequiredArgsConstructor
public class NaceDetailsController
{
    @Autowired
    private NaceService naceService;

    @PutMapping (path ="/",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Long>> putNaceDetails( @RequestBody List<NaceEntity> naceEntityList) {
       return new ResponseEntity<>(naceService.putNaceDetails(naceEntityList), HttpStatus.CREATED);
    }

    @GetMapping(path = "/retrieve/{naceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NaceEntity> getNaceDetails(@PathVariable("naceId") long id){
        return new ResponseEntity<>(naceService.getNaceDetails(id), HttpStatus.OK);

    }
}
