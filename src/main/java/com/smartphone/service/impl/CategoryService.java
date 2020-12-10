package com.smartphone.service.impl;

import com.smartphone.converter.CategoryConverter;
import com.smartphone.dto.CategoryDTO;
import com.smartphone.entity.CategoryEntity;
import com.smartphone.repository.CategoryRepository;
import com.smartphone.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryConverter categoryConverter) {
        this.categoryRepository = categoryRepository;
        this.categoryConverter = categoryConverter;
    }

    @Override
    public List<CategoryDTO> findPaginated(Pageable pageable) {
        return categoryRepository.findAll(pageable).stream()
                .map(categoryConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream()
                .map(categoryConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CategoryDTO save(CategoryDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        if(dto.getId() != null) {
            entity = categoryRepository.findOneById(dto.getId());
        }
        entity = categoryConverter.toEntity(entity, dto);
        return categoryConverter.toDto(categoryRepository.save(entity));
    }


    @Override
    public CategoryDTO findById(long id) {
        return categoryConverter.toDto(categoryRepository.findOneById(id));
    }

    @Override
    public CategoryDTO findByCode(String code) {
        return categoryConverter.toDto(categoryRepository.findOneByCode(code));
    }

    @Override
    public int getTotalItem() {
        return (int) categoryRepository.count();
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        for(long id:ids) categoryRepository.deleteById(id);
    }
}
