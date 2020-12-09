package com.smartphone.service;

import com.smartphone.dto.RoleDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IRoleService {
    List<RoleDTO> findPaginated(Pageable pageable);
    List<RoleDTO> findAll();
    RoleDTO save(RoleDTO roleDTO);
    void delete(long[] ids);
    int getTotalItem();
    RoleDTO findById(long id);
}
