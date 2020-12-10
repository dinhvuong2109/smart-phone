package com.smartphone.converter;

import com.smartphone.dto.UserDTO;
import com.smartphone.entity.BaseEntity;
import com.smartphone.entity.RoleEntity;
import com.smartphone.entity.UserEntity;
import com.smartphone.utils.SetAuditingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserConverter {
    final private RoleConverter roleConverter;

    @Autowired
    public UserConverter(RoleConverter roleConverter) {
        this.roleConverter = roleConverter;
    }

    public UserDTO toDto(UserEntity entity) {
        UserDTO result = new UserDTO();
        result.setId(entity.getId());
        result.setUserName(entity.getUserName());
        result.setPassword(entity.getPassword());
        result.setFullName(entity.getFullName());
        result.setStatus(entity.getStatus());
        result.setRoleIds(entity.getRoles().stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toList()));
        result.setRoleCodes(entity.getRoles().stream()
                .map(RoleEntity::getCode)
                .collect(Collectors.toList()));
        //Set created, modified
        SetAuditingUtil temp = new SetAuditingUtil();
        temp.setAuditing(result, entity);
        return result;
    }

    public UserEntity toEntity(UserEntity result, UserDTO dto) {
        result.setUserName(dto.getUserName());
        result.setPassword(new BCryptPasswordEncoder(10).encode(dto.getPassword()));
        result.setFullName(dto.getFullName());
        result.setStatus(dto.getStatus());
        return result;
    }
}
