package com.smartphone.dto;

import com.smartphone.entity.RoleEntity;
import com.smartphone.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {
    private String userName;
    private String password;
    private String fullName;
    private boolean active;
    private List<GrantedAuthority> authorities;

    public MyUserDetails(UserEntity userEntity) {
        this.userName = userEntity.getUserName();
        this.password = userEntity.getPassword();
        this.fullName = userEntity.getFullName();
        this.active = userEntity.getStatus() == 1 ? true:false;
        this.authorities = userEntity.getRoles().stream()
                .map((RoleEntity role) -> new SimpleGrantedAuthority(role.getCode()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
