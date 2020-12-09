package com.smartphone.converter;

import com.smartphone.dto.PhoneDTO;
import com.smartphone.entity.PhoneEntity;
import com.smartphone.utils.SetAuditingUtil;
import org.springframework.stereotype.Component;

@Component
public class PhoneConverter {
    public PhoneDTO toDto(PhoneEntity entity) {
        PhoneDTO result = new PhoneDTO();
        result.setId(entity.getId());
        result.setName(entity.getName());
        result.setPrice(entity.getPrice());
        result.setCategoryId(entity.getCategory().getId());
        result.setCategoryCode(entity.getCategory().getCode());
        //Set created, modified
        SetAuditingUtil temp = new SetAuditingUtil();
        temp.setAuditing(result, entity);
        return result;
    }

    public PhoneEntity toEntity(PhoneDTO dto) {
        PhoneEntity result = new PhoneEntity();
        result.setName(dto.getName());
        result.setPrice(dto.getPrice());
        return result;
    }

    public PhoneEntity toEntity(PhoneEntity result, PhoneDTO dto) {
        result.setName(dto.getName());
        result.setPrice(dto.getPrice());
        return result;
    }
}
