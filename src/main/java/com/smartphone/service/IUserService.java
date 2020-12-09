package com.smartphone.service;

import com.smartphone.dto.UserDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {
    List<UserDTO> findPaginated(Pageable pageable);
    UserDTO save(UserDTO userDTO);
    void delete(long[] ids);
    int getTotalItem();
    UserDTO findById(long id);
}
