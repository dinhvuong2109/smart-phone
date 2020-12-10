package com.smartphone.controller.web;

import com.smartphone.dto.CategoryDTO;
import com.smartphone.dto.PhoneDTO;
import com.smartphone.service.ICategoryService;
import com.smartphone.service.IPhoneService;
import com.smartphone.utils.CheckCategoryUtil;
import com.smartphone.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WebController {
    private final ICategoryService categoryService;
    private final IPhoneService phoneService;

    @Autowired
    public WebController(ICategoryService categoryService, IPhoneService phoneService) {
        this.categoryService = categoryService;
        this.phoneService = phoneService;
    }

    @GetMapping("/home")
    public ModelAndView getHome(@RequestParam(value = "page", required = false) Integer page) {
        ModelAndView mav = new ModelAndView("/web/web");
        //Set category
        mav.addObject("categoryModel", categoryService.findAll());
        //Set phone find by page
        if (page == null) page = 1;
        Pageable pageable = PageRequest.of(page - 1, 6);
        mav.addObject("phoneModel", phoneService.findPaginated(pageable));
        //Set parameter to set paging
        PageUtil.showPage(mav, page, phoneService.getTotalItem(), 6);
        return mav;
    }

    @GetMapping("/home/{categoryCode}")
    public ModelAndView showCategory(@PathVariable("categoryCode") String categoryCode, @RequestParam(value = "page", required = false) Integer page) {
        //Check category code
        List<String> categoryCodeList = categoryService.findAll().stream()
                .map(CategoryDTO::getCode)
                .collect(Collectors.toList());
        if (!CheckCategoryUtil.checkCategoryCode(categoryCode, categoryCodeList))
            return new ModelAndView("redirect:/home");

        ModelAndView mav = new ModelAndView("/web/category");
        //Set phone find by page category code
        if (page == null) page = 1;
        Pageable pageable = PageRequest.of(page - 1, 6);
        List<PhoneDTO> temp = phoneService.findByCategoryPaginated(categoryCode, pageable);
        mav.addObject("categoryModel", categoryService.findByCode(categoryCode));
        mav.addObject("phoneModel", temp);
        //Set parameter to set paging
        PageUtil.showPage(mav, page, phoneService.getTotalItemByCategoryCode(categoryCode), 6);
        return mav;
    }
}
