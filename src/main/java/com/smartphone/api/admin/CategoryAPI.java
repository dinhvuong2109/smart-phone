package com.smartphone.api.admin;

import com.smartphone.dto.CategoryDTO;
import com.smartphone.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController(value = "categoryAPIOfAdmin")
@RequestMapping("api/admin/category")
public class CategoryAPI {

    @Autowired
    ICategoryService categoryService;

    @PostMapping
    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.save(categoryDTO);
    }

    @PutMapping
    public CategoryDTO updateCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.save(categoryDTO);
    }

    @DeleteMapping
    public void deleteCategory(@RequestBody long[] ids) {
        categoryService.delete(ids);
    }
}
