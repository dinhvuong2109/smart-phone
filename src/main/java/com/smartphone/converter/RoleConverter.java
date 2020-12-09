package com.smartphone.converter;

import com.smartphone.dto.RoleDTO;
import com.smartphone.entity.RoleEntity;
import com.smartphone.utils.SetAuditingUtil;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {
    public RoleDTO toDto(RoleEntity entity) {
        RoleDTO result = new RoleDTO();
        result.setId(entity.getId());
        result.setName(entity.getName());
        result.setCode(entity.getCode());
        //Set created, modified
        SetAuditingUtil temp = new SetAuditingUtil();
        temp.setAuditing(result, entity);
        return result;
    }

    public RoleEntity toEntity(RoleDTO dto) {
        RoleEntity result = new RoleEntity();
        return toEntity(result, dto);
    }

    public RoleEntity toEntity(RoleEntity result, RoleDTO dto) {
        result.setName(dto.getName());
        result.setCode(dto.getCode());
        return result;
    }
}
