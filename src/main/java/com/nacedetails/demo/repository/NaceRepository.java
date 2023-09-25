package com.nacedetails.demo.repository;

import com.nacedetails.demo.dto.NaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
@Repository
public interface NaceRepository extends JpaRepository<NaceEntity, Long> {
}
