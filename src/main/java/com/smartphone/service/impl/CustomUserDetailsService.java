package com.smartphone.service.impl;

import com.smartphone.constant.SystemConstant;
import com.smartphone.dto.MyUserDetails;
import com.smartphone.entity.UserEntity;
import com.smartphone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    final
    UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findByUserNameAndStatus(userName, SystemConstant.ACTIVE_STATUS);
        userEntity.orElseThrow(() -> new UsernameNotFoundException("Not found: "+userName));
        return userEntity.map(MyUserDetails::new).get();
    }
}
