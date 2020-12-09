package com.smartphone.api.admin;

import com.smartphone.dto.UserDTO;
import com.smartphone.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController(value = "userAPIOfAdmin")
@RequestMapping("/api/admin/user")
public class UserAPI {
    @Autowired
    private IUserService userService;

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @PutMapping
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }

    @DeleteMapping
    public void deleteUsers(@RequestBody long[] ids) {
        userService.delete(ids);
    }
}
