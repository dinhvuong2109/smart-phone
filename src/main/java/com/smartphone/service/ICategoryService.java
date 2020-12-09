package com.smartphone.service;

import com.smartphone.dto.CategoryDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ICategoryService {
    List<CategoryDTO> findPaginated(Pageable pageable);
    List<CategoryDTO> findAll();
    CategoryDTO save(CategoryDTO dto);
    CategoryDTO findById(long id);
    int getTotalItem();
    void delete(long[] ids);
}
