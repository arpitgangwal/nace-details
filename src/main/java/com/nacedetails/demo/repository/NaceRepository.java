package com.nacedetails.demo.repository;

import com.nacedetails.demo.dto.NaceEntity;
import jakarta.persistence.LockModeType;
import org.hibernate.annotations.OptimisticLock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NaceRepository extends JpaRepository<NaceEntity, Long> {
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    Optional<NaceEntity> findWithLockingByNaceId(Long naceId);
}
