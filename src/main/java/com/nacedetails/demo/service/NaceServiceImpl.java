package com.nacedetails.demo.service;

import com.nacedetails.demo.dto.NaceEntity;
import com.nacedetails.demo.repository.NaceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service()
public class NaceServiceImpl implements NaceService {

    @Autowired private NaceRepository naceRepository;


    @Override
    public List<NaceEntity> putNaceDetails(List<NaceEntity> nace) {
        return naceRepository.saveAll(nace);
    }

    @Override
    public NaceEntity getNaceDetails(Long naceId) {
        return naceRepository.findById(naceId).orElseThrow();
    }
}
