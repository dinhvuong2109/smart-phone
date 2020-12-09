package com.smartphone.service.impl;

import com.smartphone.converter.RoleConverter;
import com.smartphone.dto.RoleDTO;
import com.smartphone.entity.CategoryEntity;
import com.smartphone.entity.RoleEntity;
import com.smartphone.repository.RoleRepository;
import com.smartphone.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleService implements IRoleService {
    private final RoleRepository roleRepository;
    private final RoleConverter roleConverter;

    @Autowired
    public RoleService(RoleRepository roleRepository, RoleConverter roleConverter) {
        this.roleRepository = roleRepository;
        this.roleConverter = roleConverter;
    }

    @Override
    public List<RoleDTO> findPaginated(Pageable pageable) {
        return roleRepository.findAll(pageable).stream()
                .map(roleConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RoleDTO> findAll() {
        return roleRepository.findAll().stream()
                .map(roleConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public RoleDTO save(RoleDTO dto) {
        RoleEntity entity = new RoleEntity();
        if (dto.getId() != null) {
            entity = roleRepository.findOneById(dto.getId());
        }
        entity = roleConverter.toEntity(entity, dto);
        return roleConverter.toDto(roleRepository.save(entity));
    }


    @Override
    public RoleDTO findById(long id) {
        return roleConverter.toDto(roleRepository.findOneById(id));
    }

    @Override
    public int getTotalItem() {
        return (int) roleRepository.count();
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        for (long id : ids) roleRepository.deleteById(id);
    }
}
