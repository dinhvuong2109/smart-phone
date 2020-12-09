package com.smartphone.api.admin;

import com.smartphone.dto.PhoneDTO;
import com.smartphone.service.IPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController(value = "phoneAPIOfAdmin")
@RequestMapping("/api/admin/phone")
public class PhoneAPI {
    private final IPhoneService phoneService;

    @Autowired
    public PhoneAPI(IPhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @PostMapping
    public PhoneDTO createPhone(@RequestBody PhoneDTO phoneDTO) {
        return phoneService.save(phoneDTO);
    }

    @PutMapping
    public PhoneDTO updatePhone(@RequestBody PhoneDTO phoneDTO) {
        return phoneService.save(phoneDTO);
    }

    @DeleteMapping
    public void deletePhones(@RequestBody long[] ids) {
        phoneService.delete(ids);
    }
}
