package com.smartphone.controller.admin;

import com.smartphone.dto.PhoneDTO;
import com.smartphone.service.ICategoryService;
import com.smartphone.service.IPhoneService;
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
public class PhoneController {
    private final IPhoneService phoneService;
    private final ICategoryService categoryService;

    @Autowired
    public PhoneController(IPhoneService phoneService, ICategoryService categoryService) {
        this.phoneService = phoneService;
        this.categoryService = categoryService;
    }

    @GetMapping("/admin/phone/list")
    public ModelAndView showPhone(@RequestParam(value = "page") int page, @RequestParam(value = "sortField") String sortField,
                                  @RequestParam(value = "sortDir") String sortDir, HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("/admin/phone/list");
        //Set sort
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        //Set phone find by page, hardcode pageSize = 6
        Pageable pageable = PageRequest.of(page - 1, 6, sort);
        mav.addObject("phoneModel", phoneService.findPaginated(pageable));
        //Set parameter to set paging
        PageUtil.showPage(mav, page, phoneService.getTotalItem(), 6, sortField, sortDir);
        //Show Message
        MessageUtil.showMessage(mav, req);
        return mav;
    }

    @GetMapping("/admin/phone/edit")
    public ModelAndView editPhone(@RequestParam(value = "id", required = false) Long id, HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("/admin/phone/edit");
        MessageUtil.showMessage(mav, req);
        PhoneDTO phoneDTO = new PhoneDTO();
        if (id != null) {
            phoneDTO = phoneService.findById(id);
        }
        mav.addObject("categories", categoryService.findAll());
        mav.addObject("phoneModel", phoneDTO);
        return mav;
    }
}
