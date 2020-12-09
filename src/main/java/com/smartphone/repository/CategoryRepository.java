package com.smartphone.repository;

import com.smartphone.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findOneById(long id);
    CategoryEntity findOneByCode(String code);
}
