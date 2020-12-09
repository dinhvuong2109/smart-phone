package com.smartphone.converter;

import com.smartphone.dto.CategoryDTO;
import com.smartphone.entity.CategoryEntity;
import com.smartphone.utils.SetAuditingUtil;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {
    public CategoryDTO toDto(CategoryEntity entity) {
        CategoryDTO result = new CategoryDTO();
        result.setId(entity.getId());
        result.setName(entity.getName());
        result.setCode(entity.getCode());
        //Set created, modified
        SetAuditingUtil temp = new SetAuditingUtil();
        temp.setAuditing(result, entity);
        return result;
    }

    public CategoryEntity toEntity(CategoryDTO dto) {
        CategoryEntity result = new CategoryEntity();
        return toEntity(result, dto);
    }

    public CategoryEntity toEntity(CategoryEntity entity, CategoryDTO dto) {
        entity.setName(dto.getName());
        entity.setCode(dto.getCode());
        return entity;
    }
}
