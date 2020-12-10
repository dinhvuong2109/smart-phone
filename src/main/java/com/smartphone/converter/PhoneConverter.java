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
        result.setPrice(getString(entity.getPrice()));
        result.setCategoryId(entity.getCategory().getId());
        result.setCategoryCode(entity.getCategory().getCode());
        //Set created, modified
        SetAuditingUtil temp = new SetAuditingUtil();
        temp.setAuditing(result, entity);
        return result;
    }

    public PhoneEntity toEntity(PhoneEntity result, PhoneDTO dto) {
        result.setName(dto.getName());
        result.setPrice(getLong(dto.getPrice()));
        return result;
    }

    //Function to converter long price (entity) to string price (dto)
    //Price string like 12.345.012
    private static String getString(Long t) {
        StringBuilder price=new StringBuilder();
        String tt = t.toString();
        int n = tt.length();
        int dem = 0;
        for(int i=n; i>0; i--) {
            price.insert(0, tt.charAt(i-1));
            dem++;
            if(dem==3 && i>1) {
                price.insert(0, ".");
                dem=0;
            }
        }
        return price.toString();
    }

    //Function to converter string price (dto) to long price (entity)
    private static Long getLong(String price) {
        return Long.parseLong(price.replaceAll("\\.", ""));
    }
}
