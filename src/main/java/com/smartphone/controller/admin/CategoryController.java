package com.smartphone.controller.admin;

import com.smartphone.dto.CategoryDTO;
import com.smartphone.service.ICategoryService;
import com.smartphone.utils.MessageUtil;
import com.smartphone.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CategoryController {
    private final ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/admin/category/list")
    public ModelAndView showCategoryList(@RequestParam(value = "page") int page, @RequestParam(value = "sortField") String sortField,
                                         @RequestParam(value = "sortDir") String sortDir, HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("/admin/category/list");
        //Set sort
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        //Set category find by page, hardcode pageSize = 6
        Pageable pageable = PageRequest.of(page - 1, 6, sort);
        mav.addObject("categoryModel", categoryService.findPaginated(pageable));
        //Set parameter to set paging
        PageUtil.showPage(mav, page, categoryService.getTotalItem(), 6, sortField, sortDir);
        //Show Message
        MessageUtil.showMessage(mav, req);
        return mav;
    }

    @GetMapping("/admin/category/edit")
    public ModelAndView editCategoryList(@RequestParam(value = "id", required = false) Long id, HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("/admin/category/edit");
        MessageUtil.showMessage(mav, req);
        CategoryDTO categoryDTO = new CategoryDTO();
        if (id != null) {
            categoryDTO = categoryService.findById(id);
        }
        mav.addObject("categoryModel", categoryDTO);
        return mav;
    }
}
