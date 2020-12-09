package com.smartphone.utils;

import com.smartphone.dto.AbstractDTO;
import com.smartphone.entity.BaseEntity;

public class SetAuditingUtil {
    public <T extends AbstractDTO<T>, E extends BaseEntity> void setAuditing(T result, E entity) {
        try {
            result.setCreatedBy(entity.getCreatedBy());
            result.setCreatedDate(entity.getCreatedDate());
            result.setModifiedBy(entity.getModifiedBy());
            result.setModifiedDate(entity.getModifiedDate());
        } catch (Exception e) {
            System.out.println("Fail SetAuditingUtil.");
        }
    }
}
