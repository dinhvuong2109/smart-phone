package com.smartphone.controller.admin;

import com.smartphone.dto.UserDTO;
import com.smartphone.service.IRoleService;
import com.smartphone.service.IUserService;
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
public class UserController {
    private final IUserService userService;
    private final IRoleService roleService;

    @Autowired
    public UserController(IUserService userService, IRoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin/user/list")
    public ModelAndView showUser(@RequestParam(value = "page") int page, @RequestParam(value = "sortField") String sortField,
                                 @RequestParam(value = "sortDir") String sortDir, HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("/admin/user/list");
        //Set sort
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        //Set user find by page
        Pageable pageable = PageRequest.of(page - 1, 6, sort);
        mav.addObject("userModel", userService.findPaginated(pageable));
        //Set parameter to set paging
        PageUtil.showPage(mav, page, userService.getTotalItem(), 6, sortField, sortDir);
        //Show Message
        MessageUtil.showMessage(mav, req);
        return mav;
    }

    @GetMapping("/admin/user/edit")
    public ModelAndView editUser(@RequestParam(value = "id", required = false) Long id, HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("/admin/user/edit");
        MessageUtil.showMessage(mav, req);
        UserDTO userDTO = new UserDTO();
        if (id != null) {
            userDTO = userService.findById(id);
        }
        mav.addObject("roles", roleService.findAll());
        mav.addObject("userModel", userDTO);
        return mav;
    }
}
