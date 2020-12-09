package com.smartphone.repository;

import com.smartphone.entity.PhoneEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhoneRepository extends JpaRepository<PhoneEntity, Long> {
    PhoneEntity findOneById(long id);
    Page<PhoneEntity> findPaginatedByCategoryId(Long category_id, Pageable pageable);
    int countByCategoryCode(String categoryCode);
}
