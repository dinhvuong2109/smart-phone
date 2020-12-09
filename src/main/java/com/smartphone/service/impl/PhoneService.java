package com.smartphone.service.impl;

import com.smartphone.converter.PhoneConverter;
import com.smartphone.dto.PhoneDTO;
import com.smartphone.entity.PhoneEntity;
import com.smartphone.repository.CategoryRepository;
import com.smartphone.repository.PhoneRepository;
import com.smartphone.service.IPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhoneService implements IPhoneService {
    private final PhoneConverter phoneConverter;
    private final CategoryRepository categoryRepository;
    private final PhoneRepository phoneRepository;

    @Autowired
    public PhoneService(PhoneConverter phoneConverter, CategoryRepository categoryRepository, PhoneRepository phoneRepository) {
        this.phoneConverter = phoneConverter;
        this.categoryRepository = categoryRepository;
        this.phoneRepository = phoneRepository;
    }

    @Override
    public List<PhoneDTO> findPaginated(Pageable pageable) {
        return phoneRepository.findAll(pageable).getContent().stream()
                .map(phoneConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PhoneDTO> findByCategoryPaginated(String categoryCode, Pageable pageable) {
        return phoneRepository.findPaginatedByCategoryId(categoryRepository.findOneByCode(categoryCode).getId(), pageable)
                .getContent().stream()
                .map(phoneConverter :: toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PhoneDTO save(PhoneDTO dto) {
        PhoneEntity entity = new PhoneEntity();
        if (dto.getId() != null) {
            entity = phoneRepository.findOneById(dto.getId());
        }
        entity = phoneConverter.toEntity(entity, dto);
        entity.setCategory(categoryRepository.findOneByCode(dto.getCategoryCode()));
        phoneRepository.save(entity);
        return phoneConverter.toDto(entity);
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        for(long id:ids) phoneRepository.deleteById(id);
    }

    @Override
    public int getTotalItem() {
        return (int) phoneRepository.count();
    }

    @Override
    public PhoneDTO findById(long id) {
        return phoneConverter.toDto(phoneRepository.findOneById(id));
    }

    @Override
    public int getTotalItemByCategoryCode(String categoryCode) {
        return phoneRepository.countByCategoryCode(categoryCode);
    }
}
