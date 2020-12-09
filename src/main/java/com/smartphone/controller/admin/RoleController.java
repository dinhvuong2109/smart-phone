package com.smartphone.controller.admin;

import com.smartphone.dto.RoleDTO;
import com.smartphone.service.IRoleService;
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
public class RoleController {
    private final IRoleService roleService;

    @Autowired
    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/admin/role/list")
    public ModelAndView showRoleList(@RequestParam(value = "page") int page, @RequestParam(value = "sortField") String sortField,
                                     @RequestParam(value = "sortDir") String sortDir, HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("/admin/role/list");
        //Set sort
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        //Set role find by page
        Pageable pageable = PageRequest.of(page - 1, 6, sort);
        mav.addObject("roleModel", roleService.findPaginated(pageable));
        //Set parameter to set paging
        PageUtil.showPage(mav, page, roleService.getTotalItem(), 6, sortField, sortDir);
        //Show Message
        MessageUtil.showMessage(mav, req);
        return mav;
    }

    @GetMapping("/admin/role/edit")
    public ModelAndView editRoleList(@RequestParam(value = "id", required = false) Long id, HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("/admin/role/edit");
        MessageUtil.showMessage(mav, req);
        RoleDTO roleDTO = new RoleDTO();
        if (id != null) {
            roleDTO = roleService.findById(id);
        }
        mav.addObject("roleModel", roleDTO);
        return mav;
    }
}
