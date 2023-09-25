package com.nacedetails.demo.service;



import com.nacedetails.demo.dto.NaceEntity;

import java.util.List;

public interface NaceService {

     List<NaceEntity> putNaceDetails(List<NaceEntity> nace);



     NaceEntity getNaceDetails(Long naceId);
}
