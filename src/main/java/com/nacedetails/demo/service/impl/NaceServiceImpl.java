package com.nacedetails.demo.service.impl;

import com.nacedetails.demo.dto.NaceEntity;
import com.nacedetails.demo.repository.NaceRepository;
import com.nacedetails.demo.service.NaceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class NaceServiceImpl implements NaceService {

    @Autowired private NaceRepository naceRepository;
    @CacheEvict(value="naceEntity", key="#naceId")
    @Override
    public List<Long> putNaceDetails(List<NaceEntity> naceList) {
       List<NaceEntity>  filterNaceEnityList = naceList.stream().filter(nace -> nace.getNaceId() !=null).collect(Collectors.toList());
        return naceRepository.saveAll(filterNaceEnityList).stream().map(NaceEntity::getNaceId).collect(Collectors.toList());
    }
    @Cacheable(key="#naceId", value ="naceEntity")
    @Override
    public NaceEntity getNaceDetails(Long naceId) {
        return naceRepository.findWithLockingByNaceId(naceId).orElseThrow();
    }
}
