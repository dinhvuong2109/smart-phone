package com.smartphone.service.impl;

import com.smartphone.converter.UserConverter;
import com.smartphone.dto.UserDTO;
import com.smartphone.entity.RoleEntity;
import com.smartphone.entity.UserEntity;
import com.smartphone.repository.RoleRepository;
import com.smartphone.repository.UserRepository;
import com.smartphone.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, UserConverter userConverter, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<UserDTO> findPaginated(Pageable pageable) {
        return userRepository.findAll(pageable).stream()
                .map(userConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserDTO save(UserDTO dto) {
        UserEntity entity = new UserEntity();
        if (dto.getId() != null) {
            entity = userRepository.findOneById(dto.getId());
        }
        List<RoleEntity> roles = new ArrayList<>();
        for(String roleCode: dto.getRoleCodes()) {
            roles.add(roleRepository.findOneByCode(roleCode));
        }
        entity.setRoles(roles);
        entity = userConverter.toEntity(entity, dto);
        return userConverter.toDto(userRepository.save(entity));
    }


    @Override
    public UserDTO findById(long id) {
        return userConverter.toDto(userRepository.findOneById(id));
    }

    @Override
    public int getTotalItem() {
        return (int) userRepository.count();
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        for(long id:ids) userRepository.deleteById(id);
    }
}
