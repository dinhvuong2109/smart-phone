package com.smartphone.api.admin;

import com.smartphone.dto.RoleDTO;
import com.smartphone.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController(value = "roleAPIOfAdmin")
@RequestMapping("api/admin/role")
public class RoleAPI {

    @Autowired
    IRoleService roleService;

    @PostMapping
    public RoleDTO createRole(@RequestBody RoleDTO roleDTO) {
        return roleService.save(roleDTO);
    }

    @PutMapping
    public RoleDTO updateRole(@RequestBody RoleDTO roleDTO) {
        return roleService.save(roleDTO);
    }

    @DeleteMapping
    public void deleteRole(@RequestBody long[] ids) {
        roleService.delete(ids);
    }
}
