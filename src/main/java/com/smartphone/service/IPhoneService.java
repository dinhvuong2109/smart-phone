package com.smartphone.service;

import com.smartphone.dto.PhoneDTO;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IPhoneService {
    List<PhoneDTO> findPaginated(Pageable pageable);
    List<PhoneDTO> findByCategoryPaginated(String categoryCode, Pageable pageable);
    PhoneDTO save(PhoneDTO phoneDTO);
    void delete(long[] ids);
    int getTotalItem();
    PhoneDTO findById(long id);
    int getTotalItemByCategoryCode(String categoryCode);
}
