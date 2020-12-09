package com.smartphone.repository;

import com.smartphone.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(String name);
    RoleEntity findOneById(long id);
    RoleEntity findOneByCode(String code);
}
